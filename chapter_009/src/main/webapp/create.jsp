
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    ID: <input type="text" name="id" placeholder="leave blank default" size=60>
    <br>
    Name : <input type="text" name="name" placeholder="enter the name (required)" size=60>
    <br>
    Email : <input type="text" name="email" placeholder="enter the email" size=60>
    <br>
    <input type="submit" value="add the new user">
</form>
</body>
</html>