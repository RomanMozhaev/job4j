package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * the servlet for creating update form
 */
public class UserUpdateFormServlet extends HttpServlet {

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
        String id = req.getParameter("id");
        User user = this.validate.findById(Integer.parseInt(id));
        String jspPath;
        if (user != null) {
            req.setAttribute("user", user);
            jspPath = "/WEB-INF/update.jsp";
        } else {
            req.setAttribute("message", "The user was not found.");
            jspPath = "/WEB-INF/result.jsp";
        }
        this.getServletContext().getRequestDispatcher(jspPath).forward(req, resp);

    }
}
