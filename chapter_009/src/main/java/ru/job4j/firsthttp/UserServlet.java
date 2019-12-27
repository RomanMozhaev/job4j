package ru.job4j.firsthttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();
    private final Map<String, Function<User, Boolean>> actions;

    public UserServlet() {
        this.actions = new HashMap<>();
        this.actions.put("add", add());
        this.actions.put("update", update());
        this.actions.put("delete", delete());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String list = this.validate.findAll();
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(list);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response;
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String srgId = req.getParameter("id");
        int id = Integer.getInteger(srgId, -1);
        User user = new User(id, name, email, -1);
        if (this.actions.containsKey(action)) {
            if(this.actions.get(action).apply(user)) {
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

    private Function<User, Boolean> update() {
        return validate::update;
    }
    private Function<User, Boolean> delete() {
        return validate::delete;
    }
    private Function<User, Boolean> add() {
        return validate::add;
    }

}
