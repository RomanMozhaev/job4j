<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Do you really want to delete this user?
<br>
<form action="${pageContext.servletContext.contextPath}/delete?action=delete&id=${user.id}"
      method="post">
    <input type="text" name="id" value="<c:out value="${user.id}"></c:out>" size=60 disabled>
    <br>
    Name : <input type="text" name="name" value="<c:out value="${user.name}"></c:out>" size=60 disabled>
    <br>
    Email : <input type="text" name="email" value="<c:out value="${user.email}"></c:out>" size=60 disabled>
    <br>
    <input type="submit" value="delete">
</form>
</body>
</html>
