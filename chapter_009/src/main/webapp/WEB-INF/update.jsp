<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update?action=edit
    &id=${user.id}" method="post">
    ID : <input type="text" name="id"
                value="<c:out value="${user.id}"></c:out>"
                size=60 disabled>
    <br>
    Name : <input type="text" name="name"
                  value="<c:out value="${user.name}"></c:out>"
                  size=60>
    <br>
    Email : <input type="text" name="email"
                   value="<c:out value="${user.email}"></c:out>"
                   size=60>
    <br>
    <input type="submit" value="commit">
</form>
</body>
</html>