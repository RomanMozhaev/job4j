<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.job4j.firsthttp.ValidateService" %>
<%@ page import="ru.job4j.firsthttp.User" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create.jsp" method="get">
    <input type="submit" value="Add a new user">
</form>
<table border="1" bordercolor="#000000" cellspacing="0" cellpadding="2" >
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Email</td>
        <td>Date</td>
    </tr>
    <%
        for (Map.Entry entry : ValidateService.getInstance().findAll().entrySet()) {
            User user = (User) entry.getValue();
            DateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = timeStamp.format(new Date(user.getCreateDate()));
    %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=time%></td>
        <td>
            <form action=<%=request.getContextPath()%>/delete?id=<%=user.getId()%> method="post">
                <input type="submit" value="delete">
            </form>
        </td>
        <td>
            <form action=<%=request.getContextPath()%>/update?id=<%=user.getId()%> method="post">
                <input type="submit" value="update">
            </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>