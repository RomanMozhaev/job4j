package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * the servlet for adding, updating and deleting users from the memory.
 */
public class UserServlet extends HttpServlet {

    /**
     * the instance with working methods.
     */
    private final Validate validate = ValidateService.getInstance();
    /**
     * the map with available functions.
     */
    private final Map<String, Function<User, Boolean>> actions;

    /**
     * the line separator.
     */
    private final static String LN = System.lineSeparator();

    /**
     * the main constructor with the actions-map initiation.
     */
    public UserServlet() {
        this.actions = new HashMap<>();
        this.actions.put("add", add());
        this.actions.put("update", update());
        this.actions.put("delete", delete());
    }

    /**
     * allows to get the all data from the memory.
     *
     * @param req  - request
     * @param resp -response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        ConcurrentHashMap<Integer, User> map = this.validate.findAll();
        map.forEach((i, user) -> {
            builder.append(user.getId());
            builder.append(LN);
            builder.append(user.getName());
            builder.append(LN);
            builder.append(user.getEmail());
            builder.append(LN);
            builder.append(user.getCreateDate());
            builder.append(LN);
            builder.append(LN);
        });
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(builder.toString());
        writer.flush();
    }

    /**
     * allows to add, delete and update users data in the memory.
     *
     * @param req  - request
     * @param resp -response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response;
        String action = req.getParameter("action");
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
        if (this.actions.containsKey(action)) {
            if (this.actions.get(action).apply(user)) {
                response = "the action was finished successfully.";
            } else {
                response = "the action was not implemented.";
            }
        } else {
            response = "the action was not identified.";
        }
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(response);
        writer.flush();
    }

    /**
     * the function for updating.
     *
     * @return
     */
    private Function<User, Boolean> update() {
        return validate::update;
    }

    /**
     * the function for deleting.
     *
     * @return
     */
    private Function<User, Boolean> delete() {
        return validate::delete;
    }

    /**
     * the function for adding.
     *
     * @return
     */
    private Function<User, Boolean> add() {
        return validate::add;
    }

}
