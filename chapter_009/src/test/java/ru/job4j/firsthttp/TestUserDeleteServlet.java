package ru.job4j.firsthttp;

import javax.servlet.ServletContext;

/**
 * The class for testing UserCreateServlet
 */
public class TestUserDeleteServlet extends UserDeleteServlet {

    @Override
    public ServletContext getServletContext() {
        return new TestServletContext();
    }
}
