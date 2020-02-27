package ru.job4j.loanapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * the servlet for executing a new loan application.
 */
public class ApplicationServlet extends HttpServlet {

    /**
     * the instance of the Application Validation class.
     */
    private final Validate validate = ApplicationValidation.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(sb::append);
        ObjectMapper mapper = new ObjectMapper();
        String json = sb.toString();
        Map map = mapper.readValue(json, Map.class);
        String country = (String) map.get("country");
        String name = (String) map.get("name");
        String surname = (String) map.get("surname");
        int pid = Integer.parseInt((String) map.get("pid"));
        int term = Integer.parseInt((String) map.get("term"));
        int sum = Integer.parseInt((String) map.get("sum"));
        String message = this.validate.blackListChecking(pid);
        if (message == null) {
            LoanApplication application = new LoanApplication(country, name, surname, pid, term, sum);
            message = this.validate.addApplication(application);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONObject status = new JSONObject();
        status.put("message", message);
        writer.append(status.toString());
        writer.flush();
    }
}
