package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String action = req.getParameter("action");
        if (action == null) {
            createUpdateForm(req, resp);
        } else {
            update(req, resp);
        }

    }

    /**
     * creates form for data modification.
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void createUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        User user = this.validate.findById(Integer.parseInt(id));
        String jspPath;
        if (user != null) {
            req.setAttribute("user", user);
            jspPath = "/update.jsp";
        } else {
            req.setAttribute("message", "The user was not found.");
            jspPath = "/result.jsp";
        }
        this.getServletContext().getRequestDispatcher(jspPath).forward(req, resp);
    }

    /**
     * updates data. if the data was not updated, creates a message.
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String srgId = req.getParameter("id");
        String message;
        if (this.validate.update(new User(intOrDef(srgId, -1), name, email))) {
            message = "The user was successfully updated.";
        } else {
            message = "The user was not updated.";
        }
        req.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
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