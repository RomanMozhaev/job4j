package ru.job4j.loanapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * the servlet for creating the application table.
 */
public class TableServlet extends HttpServlet {

    /**
     * the instance of the Application Validation class.
     */
    private final Validate validate = ApplicationValidation.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = intOrDef(req.getParameter("pid"), -1);
        ArrayList<LoanApplication> apps = null;
        if (pid == -1) {
            apps = this.validate.getAllAppList();
        } else {
            apps = this.validate.getAllByPID(pid);
        }
        req.setAttribute("apps", apps);
        this.getServletContext().getRequestDispatcher("/WEB-INF/table.jsp").forward(req, resp);

    }

    /**
     * the method converts string to int or return def
     *
     * @param srgId - the string for converting to int
     * @param def   - default int
     * @return int
     */
    private int intOrDef(String srgId, int def) {
        int id;
        try {
            id = Integer.parseInt(srgId);
        } catch (NumberFormatException e) {
            id = def;
        }
        return id;
    }
}
