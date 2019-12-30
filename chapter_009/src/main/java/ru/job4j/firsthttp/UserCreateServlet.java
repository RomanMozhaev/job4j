package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append("<br>")
                .append("<form action='")
                .append(req.getContextPath())
                .append("/create' method='post'>")
                .append("ID    : <input type='text' name='id' placeholder='leave blank default' size=60>")
                .append("<br>")
                .append("Name  : <input type='text' name='name' placeholder='enter the name (required)' size=60>")
                .append("<br>")
                .append("Email : <input type='text' name='email' placeholder='enter the email' size=60>")
                .append("<br>")
                .append("<input type='submit' value='add the new user'>")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(builder.toString());
        writer.flush();
    }

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
        StringBuilder response = new StringBuilder();
        User user = new User(id, name, email, -1);
        String message;
        if (this.validate.add(user)) {
            message = "the adding was finished successfully.";
        } else {
            message = "the adding was not implemented.";
        }
        response.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append(message)
                .append("<br>")
                .append("<form action='")
                .append(req.getContextPath())
                .append("/create' method='get'>")
                .append("<input type='submit' value='add one more user'>")
                .append("</form>")
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
        writer.append(response);
        writer.flush();
    }
}
