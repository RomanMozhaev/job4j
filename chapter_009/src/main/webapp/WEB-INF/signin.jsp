
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<br>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login : <input type="text" name="name" placeholder="enter the name" size=60>
    <br>
    Password : <input type="password" name="password" placeholder="enter the password" size=60>
    <br>
    <input type="submit" value="Sign In">
</form>
</body>
</html>