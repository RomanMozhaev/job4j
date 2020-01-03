<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    Name : <input type="text" name="name" placeholder="enter the name (required)" size=60>
    <br>
    Email : <input type="text" name="email" placeholder="enter the email" size=60>
    <br>
    <input type="submit" value="add the new user">
</form>
</body>
</html>
