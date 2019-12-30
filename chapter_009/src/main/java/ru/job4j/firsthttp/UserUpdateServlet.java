package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
     * @param req
     * @param resp
     * @throws IOException
     */
    private void createUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                    .append("<form action='")
                    .append(req.getContextPath())
                    .append("/update?action=edit&id=")
                    .append(id)
                    .append("' method='post'>")
                    .append("ID    : <input type='text' name='id' value='")
                    .append(user.getId())
                    .append("' size=60 disabled>")
                    .append("<br>")
                    .append("Name  : <input type='text' name='name' value='")
                    .append(user.getName())
                    .append("' size=60>")
                    .append("<br>")
                    .append("Email : <input type='text' name='email' value='")
                    .append(user.getEmail())
                    .append("' size=60>")
                    .append("<br>")
                    .append("<input type='submit' value='commit'>")
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
     * updates data. if the data was not updated, creates a message.
     * @param req
     * @param resp
     * @throws IOException
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String srgId = req.getParameter("id");
        int intId = Integer.parseInt(srgId);
        if (this.validate.update(new User(intId, name, email, -1))) {
            resp.sendRedirect(req.getContextPath() + "/view");
        } else {
            StringBuilder response = new StringBuilder();
            response.append("<!DOCTYPE html>")
                    .append("<html lang=\"en\">")
                    .append("<head>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<title>Title</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("The update was not finished successfully.")
                    .append("<br>")
                    .append("<form action='")
                    .append(req.getContextPath())
                    .append("/view' method='get'>")
                    .append("<input type='submit' value='see the table'>")
                    .append("</form>")
                    .append("</body>")
                    .append("</html>");
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(response.toString());
            writer.flush();
        }
    }
}
