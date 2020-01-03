package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * the servlet for creating a new user
 */
public class UsersViewServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Map.Entry<Integer, User>> users = this.validate.findAll().entrySet();
        req.setAttribute("users", users);
        this.getServletContext().getRequestDispatcher("/WEB-INF/view.jsp").forward(req, resp);
    }
}
