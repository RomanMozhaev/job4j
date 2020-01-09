<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">
    Name : <input type="text" name="name" placeholder="enter the name (required)" size=60>
    <br>
    Email : <input type="text" name="email" placeholder="enter the email" size=60>
    <br>
    <div class="checkbox">
        Photo : <input type="file" name="file">
    </div>
    <br>
    <input type="submit" value="add the new user" class="btn btn-default">
</form>
</body>
</html>