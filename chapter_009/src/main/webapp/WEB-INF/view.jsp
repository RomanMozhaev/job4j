<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        var msg = "${msg}";
        if (msg !== "") {
        alert(msg);
        }
    });
</script>
</head>
<body>
<c:if test="${error != ''}">

<%--    <div style="background-color: red">--%>
<%--        <c:out value="${error}"></c:out>--%>
<%--    </div>--%>
</c:if>
<div class="container">
    <table id="table" class="table">
        <tr>
            <form action="${pageContext.servletContext.contextPath}/create" method="get">
                <input type="submit" class="form-control" value="Add a new user">
            </form>
        </tr>
        <h2>Users</h2>
        <thead>
        <tr>
            <th>ID</th>
            <th>Role</th>
            <th>Name</th>
            <th>Email</th>
            <th>Date</th>
            <th>Picture</th>
        </tr>
        </thead>

        <c:forEach items="${users}" var="entry">
            <tr>
                <td><c:out value="${entry.value.id}"></c:out></td>
                <td><c:out value="${entry.value.role}"></c:out></td>
                <td><c:out value="${entry.value.name}"></c:out></td>
                <td><c:out value="${entry.value.email}"></c:out></td>
                <jsp:useBean id="myDate" class="java.util.Date"/>
                <c:set target="${myDate}" property="time" value="${entry.value.createDate}"/>
                <td><c:out value="${myDate}"></c:out></td>
                <td><a href="${pageContext.servletContext.contextPath}/download?photoId=${entry.value.photoId}">
                    <img src="${pageContext.servletContext.contextPath}/download?photoId=${entry.value.photoId}"
                         width="100px" height="100px"/></a></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/delete?id=${entry.value.id}" method="post">
                        <input type="submit" class="form-control" value="delete">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/updateForm?id=${entry.value.id}"
                          method="post">
                        <input type="submit" class="form-control" value="update">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>