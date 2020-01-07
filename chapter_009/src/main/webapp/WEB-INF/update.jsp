<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update"
      method="post" enctype="multipart/form-data">
    <a href="${pageContext.servletContext.contextPath}/download?photoId=${user.photoId}">
        <img src="${pageContext.servletContext.contextPath}/download?photoId=${user.photoId}"
             width="100px" height="100px"/></a>
    <br>
    ID : <input type="text" name="id"
                value="<c:out value="${user.id}"></c:out>"
                size=60 readonly>
    <br>
    Name : <input type="text" name="name"
                  value="<c:out value="${user.name}"></c:out>"
                  size=60>
    <br>
    Email : <input type="text" name="email"
                   value="<c:out value="${user.email}"></c:out>"
                   size=60>
    <br>
    <div class="checkbox">
        Photo : <input type="file" name="file">
    </div>
    <br>
    <input type="submit" value="commit" class="btn btn-default">
</form>
</body>
</html>
