<%@ page import="ru.job4j.firsthttp.UserCreateServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="${message}"></c:out>
<br>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="add one more user">
</form>
<br>
<form action="${pageContext.servletContext.contextPath}/view" method="get">
    <input type="submit" value="see the table">
</form>
</body>
</html>