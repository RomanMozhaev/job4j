<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>delete</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function deleteUser() {
            var url = "${pageContext.servletContext.contextPath}/delete?action=delete&id=" + $('#id').val();
            $.ajax({
                type: "post",
                url: url,
                success: [function ($data) {
                    resultProcessing($data);
                }],
                contentType: 'json',
            });
        }

        function resultProcessing($data) {
            if ($data['status'] === 'valid') {
                alert("The user was deleted successfully.");
            } else {
                alert("The user was not deleted. Please try again.");
            }
            returnToUsers();
        }

        function returnToUsers() {
            var url = "${pageContext.servletContext.contextPath}/";
            document.location.href = url;
        }
    </script>
</head>
<body>
<div class="container">
    <table class="table">
        <tr>
            <h2>Do you really want to delete this user?</h2>
        </tr>
        <tr>
            <td>
                <div class="form-group">
                    <label for="id">ID: </label>
                    <input type="text" class="form-control" name="id" id="id"
                           value="<c:out value="${user.id}"></c:out>" readonly>
                </div>
                <div class="form-group">
                    <label for="role">Role: </label>
                    <input type="text" class="form-control" id="role" name="role"
                           value="<c:out value="${user.role}"></c:out>" readonly>
                </div>
                <div class="form-group">
                    <label for="name">Name: </label>
                    <input type="text" class="form-control" name="name" id="name"
                           value="<c:out value="${user.name}"></c:out>" readonly>
                </div>
                <div class="form-group">
                    <label for="email">Email: </label>
                    <input type="text" class="form-control" name="email" id="email"
                           value="<c:out value="${user.email}"></c:out>" readonly>
                </div>
            </td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/download?photoId=${user.photoId}">
                    <img src="${pageContext.servletContext.contextPath}/download?photoId=${user.photoId}"
                         width="300px" height="300px"/></a>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" onclick="deleteUser()" class="form-control">delete</button>
            </td>
            <td>
                <button type="submit" class="form-control" onclick="returnToUsers()">return</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>