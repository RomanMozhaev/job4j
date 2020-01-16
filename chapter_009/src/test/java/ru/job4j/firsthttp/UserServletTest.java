package ru.job4j.firsthttp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class, Upload.class, HttpSession.class})
@PowerMockIgnore({"org.apache.logging.log4j.*", "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.dom.*", "javax.management.*"})
public class UserServletTest {

    /**
     * this test for UserCreateServlet.
     * ValidateStub - stub for ValidateService
     * TestUpload - stab for Upload
     *
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenCreateThenReturnName() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        Upload testUpload = new TestUpload();
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.mockStatic(Upload.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        when(Upload.getUploadInstance()).thenReturn(testUpload);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(context.getAttribute("javax.servlet.context.tempdir")).thenReturn(new File(""));
        when(context.getRequestDispatcher("/WEB-INF/createResult.jsp")).thenReturn(dispatcher);
        UserCreateServlet servlet = new UserCreateServlet();
        servlet.doPost(request, response);
        Map<Integer, User> map = validate.findAll();
        String resultName = map.entrySet().iterator().next().getValue().getName();
        assertThat(resultName, is("User Test"));
    }

    /**
     * this test for UserUpdateServlet.
     * ValidateStub - stub for ValidateService
     * TestUpload - stab for Upload
     *
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenUpdateThenReturnNewName() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        Upload testUpload = new TestUpload();
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.mockStatic(Upload.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        when(Upload.getUploadInstance()).thenReturn(testUpload);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(context.getAttribute("javax.servlet.context.tempdir")).thenReturn(new File(""));
        when(context.getRequestDispatcher("/WEB-INF/result.jsp")).thenReturn(dispatcher);
        validate.add(new User("User1", "user@mail", "", "password", "user"));
        UserUpdateServlet servlet = new UserUpdateServlet();
        servlet.doPost(request, response);
        Map<Integer, User> map = validate.findAll();
        String resultName = map.entrySet().iterator().next().getValue().getName();
        assertThat(resultName, is("User Test"));
    }

    /**
     * this test for UserDeleteServlet.
     * ValidateStub - stub for ValidateService
     *
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenDeleteThenNUserFound() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/WEB-INF/result.jsp")).thenReturn(dispatcher);
        when(context.getRequestDispatcher("/WEB-INF/delete.jsp")).thenReturn(dispatcher);
        validate.add(new User("User1", "user@mail", "", "password", "user"));
        int previousSize = validate.findAll().size();
        UserDeleteServlet servlet = new UserDeleteServlet();
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("1");
        servlet.doPost(request, response);
        int currentSize = validate.findAll().size();
        assertThat(currentSize, is(previousSize - 1));
    }
}