package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * the servlet for creating a new user
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String srgId = req.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(srgId);
        } catch (NumberFormatException e) {
            id = -1;
        }
        User user = new User(id, name, email, -1);
        String message;
        if (this.validate.add(user)) {
            message = "the adding was finished successfully.";
        } else {
            message = "the adding was not implemented.";
        }
        req.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/createResult.jsp").forward(req, resp);
    }
}
