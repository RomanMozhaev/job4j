<%@ page import="ru.job4j.firsthttp.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%User user = (User) request.getAttribute("user");%>
<form action="<%=request.getContextPath()%>/update?action=edit
    &id=<%=user.getId()%>" method="post">
    ID : <input type="text" name="id"
                value="<%=user.getId()%>"
                size=60 disabled>
    <br>
    Name : <input type="text" name="name"
                  value="<%=user.getName()%>"
                  size=60>
    <br>
    Email : <input type="text" name="email"
                   value="<%=user.getEmail()%>"
                   size=60>
    <br>
    <input type="submit" value="commit">
</form>
</body>
</html>