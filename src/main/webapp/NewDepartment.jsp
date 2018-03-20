<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.03.2018
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание нового департамента</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
Имя департамента
<form action="/DepartmentCreate" method="post">
    <input type="text" name="nameOfDepartment">
    <button class="btn btn-primary" type="submit">Create</button>
</form>
<div class="error">
    <jstl:forEach var="obj" items="${ErrorMessageList}">
        <p>${obj} </p>
    </jstl:forEach>
</div>
</body>
</html>
