<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="Add a new user">
</form>
<table border="1" bordercolor="#000000" cellspacing="0" cellpadding="2">
    <tr>
        <td>ID</td>
        <td>Role</td>
        <td>Name</td>
        <td>Email</td>
        <td>Date</td>
    </tr>
    <c:forEach items="${users}" var="entry">
        <tr>
            <td><c:out value="${entry.value.id}"></c:out></td>
            <td><c:out value="${entry.value.role}"></c:out></td>
            <td><c:out value="${entry.value.name}"></c:out></td>
            <td><c:out value="${entry.value.email}"></c:out></td>
            <td><c:out value="${entry.value.createDate}"></c:out></td>
            <td><a href="${pageContext.servletContext.contextPath}/download?photoId=${entry.value.photoId}">
                <img src="${pageContext.servletContext.contextPath}/download?photoId=${entry.value.photoId}"
                     width="100px" height="100px"/></a></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete?id=${entry.value.id}" method="post">
                    <input type="submit" value="delete">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/updateForm?id=${entry.value.id}" method="post">
                    <input type="submit" value="update">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
