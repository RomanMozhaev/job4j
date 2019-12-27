package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    private void createDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder builder = new StringBuilder();
        String id = req.getParameter("id");
        User user = this.validate.findById(Integer.parseInt(id));
        if (user != null) {
            builder.append("<!DOCTYPE html>")
                    .append("<html lang=\"en\">")
                    .append("<head>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<title>Title</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("Do you really want to delete this user?")
                    .append("<br>")
                    .append("<form action='")
                    .append(req.getContextPath())
                    .append("/delete?action=delete&id=")
                    .append(id)
                    .append("' method='post'>")
                    .append("ID    : <input type='text' name='id' value='")
                    .append(user.getId())
                    .append("' size=60 disabled>")
                    .append("<br>")
                    .append("Name  : <input type='text' name='name' value='")
                    .append(user.getName())
                    .append("' size=60 disabled>")
                    .append("<br>")
                    .append("Email : <input type='text' name='email' value='")
                    .append(user.getEmail())
                    .append("' size=60 disabled>")
                    .append("<br>")
                    .append("<input type='submit' value='delete'>")
                    .append("</form>")
                    .append("</body>")
                    .append("</html>");
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(builder);
            writer.flush();
        } else {
            resp.sendRedirect(req.getContextPath() + "/view");
        }
    }

    /**
     * deletes the user.
     * @param req
     * @param resp
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String strId = req.getParameter("id");
        int id = Integer.parseInt(strId);
        this.validate.delete(new User(id, null, null, -1));
        resp.sendRedirect(req.getContextPath() + "/view");
    }
}
