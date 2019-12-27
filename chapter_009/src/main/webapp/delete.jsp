<%@ page import="ru.job4j.firsthttp.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Do you really want to delete this user?
<%User user = (User) request.getAttribute("user");%>
<br>
<form action="<%=request.getContextPath()%>/delete?action=delete&id=<%=user.getId()%>"
      method="post">
    <input type="text" name="id" value="<%=user.getId()%>" size=60 disabled>
    <br>
    Name : <input type="text" name="name" value="<%=user.getName()%>" size=60 disabled>
    <br>
    Email : <input type="text" name="email" value="<%=user.getEmail()%>" size=60 disabled>
    <br>
    <input type="submit" value="delete">
</form>
</body>
</html>
