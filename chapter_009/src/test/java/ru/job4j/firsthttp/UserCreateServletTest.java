//package ru.job4j.firsthttp;
//
//import org.apache.commons.fileupload.FileItem;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ValidateService.class, Upload.class, UserCreateServlet.class})
//@PowerMockIgnore({"org.apache.logging.log4j.*", "com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.dom.*", "javax.management.*"})
//public class UserCreateServletTest {
//
//    @Test
//    public void whenThen() throws ServletException, IOException {
//
//        Validate validate = new ValidateStub();
//        PowerMockito.mockStatic(ValidateService.class);
//        when(ValidateService.getInstance()).thenReturn(validate);
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        File repository = mock(File.class);
//        FileItem item = mock(FileItem.class);
//        Map<String, Object> fields = mock(HashMap.class);
//        Upload upload = mock(Upload.class);
//        when(upload.getFields(request, repository)).thenReturn(fields);
//        when(upload.uploadPhoto(item, repository)).thenReturn("");
//        when(fields.get("name")).thenReturn("User Test");
//        UserCreateServlet servlet = new UserCreateServlet();
//        ContextStub context = new ContextStub();
//        servlet.init(context);
//        servlet.doPost(request, response);
//        Map<Integer, User> map = validate.findAll();
//        String resultName = map.entrySet().iterator().next().getValue().getName();
//        assertThat(resultName, is("User Test"));
//    }
//}