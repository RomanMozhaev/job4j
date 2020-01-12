package ru.job4j.firsthttp;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
        Upload upload = new Upload();
        File repository = (File) this.getServletConfig().getServletContext().getAttribute("javax.servlet.context.tempdir");
        Map<String, Object> fields = upload.getFields(req, repository);
        String name = (String) fields.get("name");
        String email = (String) fields.get("email");
        String srgId = (String) fields.get("id");
        FileItem photoId = (FileItem) fields.get("photoId");
        String password = (String) fields.get("password");
        String role = (String) fields.get("role");
        if (role == null) {
            role = "";
        }
        String message;
        String photoPath = upload.uploadPhoto(photoId, repository);
        User savedUser = this.validate.findById(intOrDef(srgId, -1));
        String savedPhoto = savedUser.getPhotoId();

        if (this.validate.update(new User(intOrDef(srgId, -1), name, email, photoPath, password, role))) {
            message = "The user was successfully updated.";
            if (!photoPath.equals("")) {
                new File(savedPhoto).delete();
            }
        } else {
            message = "The user was not updated.";
            new File(photoPath).delete();
        }
        req.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
    }

    /**
     * the method converts string to int or return def
     * @param srgId - the string for converting to int
     * @param def - default int
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