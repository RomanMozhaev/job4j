<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>update</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var name = $('#name').val();
            var email = $('#email').val();
            var massage = "";
            if (name === "") {
                massage += "name";
            }
            if (email === "") {
                if (massage !== "") {
                    massage += ", ";
                }
                massage += "email";
            }
            if (massage !== "") {
                alert("Please enter: " + massage);
            } else {
                var formData = new FormData();
                formData.append("id", $('#id').val());
                formData.append("name", name);
                formData.append("email", email);
                var pass = $('#pass').val();
                if (pass === undefined) {
                    pass = "";
                }
                formData.append("pass", pass);
                var role = $('#role').val();
                if (role === undefined) {
                    role = "";
                }
                formData.append("role", role);
                var pic = document.getElementById("pic").files[0];
                if (pic !== undefined) {
                    formData.append("pic", pic);
                }
                response(formData);
            }
        }

        function response(formData) {
            var url = "${pageContext.servletContext.contextPath}/update";
            $.ajax({
                type: "post",
                url: url,
                data: formData,
                success: [function ($data) {
                    resultProcessing($data);
                }],
                contentType: false,
                processData: false,
            });
        }

        function resultProcessing($data) {
            if ($data['status'] === 'valid') {
                alert("The user was updated successfully.");
            } else {
                alert("The user was not updated. Please try again.");
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
            <td>
                <div class="form-group">
                    <label for="id">ID: </label>
                    <input type="text" class="form-control" name="id" id="id"
                           value="<c:out value="${user.id}"></c:out>" readonly>
                </div>
                <c:if test="${user.role == 'admin'}">
                    <div class="form-group">
                        <label for="role">Role: </label>
                        <select class="form-control" name="role" id="role">
                            <option value=""></option>
                            <option value="admin">user</option>
                            <option value="user">admin</option>
                        </select>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="name">Name: </label>
                    <input type="text" class="form-control" name="name" id="name"
                           value="<c:out value="${user.name}"></c:out>">
                </div>
                <div class="form-group">
                    <label for="pass">Password: </label>
                    <input type="password" class="form-control" name="password" id="pass"
                           placeholder="leave blank or enter the new password">
                </div>

                <div class="form-group">
                    <label for="email">Email: </label>
                    <input type="text" class="form-control" name="email" id="email"
                           value="<c:out value="${user.email}"></c:out>">
                </div>
                <div class="form-group">
                    <label for="pic">Photo: </label>
                    <input type="file" class="form-control" name="file" id="pic">
                </div>
            </td>
            <td>
                <div>
                    <form class="form-group" action="${pageContext.servletContext.contextPath}/update"
                          method="post" enctype="multipart/form-data">
                        <a href="${pageContext.servletContext.contextPath}/download?photoId=${user.photoId}">
                            <img src="${pageContext.servletContext.contextPath}/download?photoId=${user.photoId}"
                                 width="350px" height="350px"/></a>
                    </form>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" class="form-control" onclick="return validate()">commit</button>
            </td>
            <td>
                <button type="submit" class="form-control" onclick="returnToUsers()">return</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>