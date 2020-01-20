package ru.job4j.firsthttp;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * the servlet for user data updating
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();

    /**
     * the method for user data updating.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Upload upload = Upload.getUploadInstance();
        HttpSession session = req.getSession();
        File repository = (File) session.getServletContext().getAttribute("javax.servlet.context.tempdir");
        Map<String, Object> fields = upload.getFields(req, repository);
        String name = (String) fields.get("name");
        String email = (String) fields.get("email");
        String srgId = (String) fields.get("id");
        FileItem photoId = (FileItem) fields.get("pic");
        String password = (String) fields.get("pass");
        String role = (String) fields.get("role");
        String photoPath = upload.uploadPhoto(photoId, repository);
        User savedUser = this.validate.findById(intOrDef(srgId, -1));
        String savedPhoto = "";
        if (savedUser != null) {
            savedPhoto = savedUser.getPhotoId();
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONObject status = new JSONObject();
        if (this.validate.update(new User(intOrDef(srgId, -1), name, email, photoPath, password, role))) {
            status.put("status", "valid");
            if (!photoPath.equals("") && !savedPhoto.equals("")) {
                new File(savedPhoto).delete();
            }
        } else {
            status.put("status", "invalid");
            new File(photoPath).delete();
        }
        writer.append(status.toString());
        writer.flush();
    }

    /**
     * the method converts string to int or return def
     *
     * @param srgId - the string for converting to int
     * @param def   - default int
     * @return int
     */
    private int intOrDef(String srgId, int def) {
        int id;
        try {
            id = Integer.parseInt(srgId);
        } catch (NumberFormatException e) {
            id = def;
        }
        return id;
    }
}