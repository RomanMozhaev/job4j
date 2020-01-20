<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add a new user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var name = $('#name').val();
            var email = $('#email').val();
            var pass = $('#pass').val();
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
            if (pass === "") {
                if (massage !== "") {
                    massage += ", ";
                }
                massage += "password";
            }
            if (massage !== "") {
                alert("Please enter: " + massage);
            } else {
                var formData = new FormData();
                formData.append("name", name);
                formData.append("email", email);
                formData.append("pass", pass);
                formData.append("role", $('#role').val());
                var pic = document.getElementById("pic").files[0];
                if (pic !== undefined) {
                    formData.append("pic", pic);
                }
                response(formData);
            }
            return false;
        }

        function response(formData) {
            var url = "${pageContext.servletContext.contextPath}/create";
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
                alert("The user was added successfully.");
                resetFields();
            } else {
                alert("The user was not added. Please try again.");
            }
        }

        function resetFields() {
            var form = document.getElementById('fieldTable');
            var inputs = form.getElementsByTagName('input');
            for (let input of inputs)
                input.value = '';
        }

        function returnToUsers() {
            var url = "${pageContext.servletContext.contextPath}/";
            document.location.href = url;
        }
    </script>
</head>
<body>
<div class="container">
    <table class="table" id="fieldTable">
        <tr>
            <td>
                <div class="form-group">
                    <label for="role">Role: </label>
                    <select name="role" class="form-control" id="role">
                        <option value="user">user</option>
                        <option value="admin">admin</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="name">Name: </label>
                    <input type="text" class="form-control" name="name" placeholder="enter the name" id="name">
                </div>
                <div class="form-group">
                    <label for="email">Email: </label>
                    <input type="text" class="form-control" name="email" placeholder="enter the email" id="email">
                </div>
                <div class="form-group">
                    <label for="pass">Password: </label>
                    <input type="password" class="form-control" name="password" placeholder="enter the password"
                           id="pass">
                </div>
                <div class="form-group">
                    <label for="pic">Photo: </label>
                    <input type="file" class="form-control" name="file" id="pic">
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" onclick="return validate()" class="form-control">add the new user</button>
            </td>
            <td>
                <button type="submit" onclick="returnToUsers()" class="form-control">return</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>