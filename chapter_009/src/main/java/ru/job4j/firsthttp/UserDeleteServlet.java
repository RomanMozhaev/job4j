package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * the servlet deletes the user from the memory.
 */
public class UserDeleteServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            createDeleteForm(req, resp);
        } else {
            delete(req, resp);
        }
    }

    /**
     * shows the user's data which will be deleted.
     * @param req
     * @param resp
     * @throws IOException
     */
    private void createDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        User user = this.validate.findById(Integer.parseInt(id));
        if (user != null) {
            req.setAttribute("user", user);
            this.getServletContext().getRequestDispatcher("/WEB-INF/delete.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "The user was not found.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(req, resp);
        }
    }

    /**
     * deletes the user.
     * @param req
     * @param resp
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String stgId = req.getParameter("id");
        String message = "The user was not deleted.";
        String photo;
        User user = this.validate.findById(intOrDef(stgId, -1));
        if (user != null) {
            photo = user.getPhotoId();
            if (this.validate.delete(user)) {
                message = "The user was successfully deleted.";
                new File(photo).delete();
            }
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
