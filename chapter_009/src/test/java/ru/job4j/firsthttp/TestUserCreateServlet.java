package ru.job4j.firsthttp;

import javax.servlet.*;

/**
 * this class for overriding getServletContext
 */
public class TestUserCreateServlet extends UserCreateServlet {

    @Override
    public ServletContext getServletContext() {
        return new TestServletContext();
    }
}
