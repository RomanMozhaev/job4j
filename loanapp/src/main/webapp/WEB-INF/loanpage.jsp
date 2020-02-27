<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Loan Form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=en-EN" type="text/javascript"></script>
    <script type="text/javascript">
        var country;
        window.onload = function () {
            country = ymaps.geolocation.country;
        }
    </script>
    <script>
        function readFields() {
            var data = {
                sum: document.getElementById('sum').value,
                term: document.getElementById('term').value,
                name: document.getElementById('name').value,
                surname: document.getElementById('surname').value,
                pid: document.getElementById('pid').value,
                country: country
            };
            return data;
        }

        function validate() {
            var data = readFields();
            if (data.sum === ""
                || data.term === ""
                || data.name === ""
                || data.surname === ""
                || data.pid === "") {
                alert("Please fill all fields in");
            } else {
                sendForm(data);
            }
        }

        function sendForm(data) {
            var url = "${pageContext.servletContext.contextPath}/application";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(data),
                success: [function ($data) {
                    alert($data['message']);
                }],
                contentType: 'json',
            });
        }

        function getByPid() {
            var pid = document.getElementById('pid').value;
            if (pid === "") {
                alert("Please, fill Personal ID in.")
            } else {
                var url = "${pageContext.servletContext.contextPath}/table?pid=" + pid;
                document.location.href = url;
            }
        }

        function getTable() {
            var url = "${pageContext.servletContext.contextPath}/table";
            document.location.href = url;
        }
    </script>
</head>
<body>
<table>
    <tr>
        <h2>
            Loan application form
        </h2>
    </tr>
    <tr>
        <div>
            <label for="sum">Loan sum</label>
            <input type="number" class="form-control" name="sum" placeholder="enter the loan value in USD" id="sum">
        </div>
    </tr>
    <tr>
        <div>
            <label for="term">Loan Term</label>
            <input type="number" class="form-control" name="term" placeholder="enter the duration of the loan in months"
                   id="term">
        </div>
    </tr>
    <tr>
        <div>
            <label for="name">First Name</label>
            <input type="text" class="form-control" name="name" placeholder="enter the first name" id="name">
        </div>
    </tr>
    <tr>
        <div>
            <label for="surname">Second Name</label>
            <input type="text" class="form-control" name="surname" placeholder="enter the second name" id="surname">
        </div>
    </tr>
    <tr>
        <div>
            <label for="pid">Personal ID</label>
            <input type="number" class="form-control" name="pid" placeholder="enter the personal ID" id="pid">
        </div>
    </tr>
    <tr>
        <div>
            <button type="submit" onclick="validate()" class="form-control">Send Loan Application</button>
        </div>
    </tr>
    <tr>
        <div>
            <button type="submit" onclick="getTable()" class="form-control">All Loan Application</button>
        </div>
    </tr>
    <tr>
        <div>
            <button type="submit" onclick="getByPid()" class="form-control">Loan Application By Personal ID</button>
        </div>
    </tr>
</table>
</body>
</html>
