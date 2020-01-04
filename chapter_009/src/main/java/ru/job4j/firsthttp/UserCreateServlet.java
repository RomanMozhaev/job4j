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
        Upload upload = new Upload();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        Map<String, Object> fields = upload.getFields(req, servletContext);
        String name = (String) fields.get("name");
        String email = (String) fields.get("email");
        FileItem photoId = (FileItem) fields.get("photoId");
        String message;
        String photoPath = upload.uploadPhoto(photoId);
        User user = new User(name, email, photoPath);
        if (this.validate.add(user)) {
            message = "the adding was finished successfully.";
        } else {
            message = "the adding was not implemented.";
            new File(photoPath).delete();
        }
        req.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/createResult.jsp").forward(req, resp);
    }
}
