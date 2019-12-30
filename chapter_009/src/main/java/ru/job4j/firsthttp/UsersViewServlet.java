package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the servlet for creating the data table.
 */
public class UsersViewServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();

    /**
     * the line separator.
     */
    private final static String LN = System.lineSeparator();

    /**
     * creates the table with users' data.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConcurrentHashMap<Integer, User> map = this.validate.findAll();
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append("<form action='")
                .append(req.getContextPath())
                .append("/create' method='get'>")
                .append("<input type='submit' value='Add a new user'>")
                .append("</form>")
                .append("<table>")
                .append("<tr>")
                .append("<td>")
                .append("ID")
                .append("</td>")
                .append("<td>")
                .append("Name")
                .append("</td>")
                .append("<td>")
                .append("Email")
                .append("</td>")
                .append("<td>")
                .append("Date")
                .append("</td>")
                .append("</tr>");

        map.forEach((integer, user) -> {
            DateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = timeStamp.format(new Date(user.getCreateDate()));
            builder.append("<tr>")
                    .append("<td>")
                    .append(user.getId())
                    .append("</td>")
                    .append("<td>")
                    .append(user.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(user.getEmail())
                    .append("</td>")
                    .append("<td>")
                    .append(time)
                    .append("</td>")
                    .append("<td>")
                    .append("<form action='")
                    .append(req.getContextPath())
                    .append("/delete?id=")
                    .append(user.getId())
                    .append("' method='post'>")
                    .append("<input type='submit' value='delete'>")
                    .append("</form>")
                    .append("</td>")
                    .append("<td>")
                    .append("<form action='")
                    .append(req.getContextPath())
                    .append("/update?id=")
                    .append(user.getId())
                    .append("' method='post'>")
                    .append("<input type='submit' value='update'>")
                    .append("</form>")
                    .append("</td>")
                    .append("</tr>");
        });

        builder.append("</table>")
                .append("</body>")
                .append("</html>");
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(builder.toString());
        writer.flush();
    }
}
