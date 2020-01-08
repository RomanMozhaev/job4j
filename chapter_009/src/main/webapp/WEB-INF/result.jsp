<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="${message}"></c:out>
<br>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="see the table">
</form>
</body>
</html>