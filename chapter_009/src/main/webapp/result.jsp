<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=request.getAttribute("message")%>
<br>
<form action="<%=request.getContextPath()%>/view.jsp" method="get">
    <input type="submit" value="see the table">
</form>
</body>
</html>