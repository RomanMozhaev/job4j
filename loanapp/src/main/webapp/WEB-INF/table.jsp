<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applications</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<table>
    <div class="container">
        <table id="table" class="table">
            <h2>Applications</h2>
            <thead>
            <tr>
                <th>Personal ID</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Term</th>
                <th>Value</th>
                <th>Country</th>
            </tr>
            </thead>

            <c:forEach items="${apps}" var="app">
                <tr>
                    <td>
                        <c:out value="${app.pid}"></c:out>
                    </td>
                    <td>
                        <c:out value="${app.name}"></c:out>
                    </td>
                    <td>
                        <c:out value="${app.surname}"></c:out>
                    </td>
                    <td>
                        <c:out value="${app.term}"></c:out>
                    </td>
                    <td>
                        <c:out value="${app.sum}"></c:out>
                    </td>
                    <td>
                        <c:out value="${app.country}"></c:out>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</table>
</body>
</html>
