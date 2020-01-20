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
 * the servlet for creating a new user
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Upload upload = Upload.getUploadInstance();
        HttpSession session = req.getSession();
        File repository = (File) session.getServletContext().getAttribute("javax.servlet.context.tempdir");
        Map<String, Object> fields = upload.getFields(req, repository);
        String name = (String) fields.get("name");
        String email = (String) fields.get("email");
        FileItem photoId = (FileItem) fields.get("pic");
        String password = (String) fields.get("pass");
        String role = (String) fields.get("role");
        String photoPath = upload.uploadPhoto(photoId, repository);
        User user = new User(name, email, photoPath, password, role);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONObject status = new JSONObject();
        if (this.validate.add(user)) {
            status.put("status", "valid");
        } else {
            status.put("status", "invalid");
            new File(photoPath).delete();
        }
        writer.append(status.toString());
        writer.flush();
    }
}