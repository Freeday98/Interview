<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.03.2018
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
    <title>Отредактировать департамент</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<table>
    <form action="/DepartmentEdit" method="post">
        <tr> <td> Название департамента </td></tr>
        <tr> <td> <input type="text" name = "newName" value="${WrongValue.nameDepartment}">  </td></tr>
        <tr><td><button type="submit" class="btn btn-primary" name="id" value="${requestScope.id}">Edit</button></td>
        <td><button btn class="btn-primary" formaction="/Start" formmethod="get">Back to departments</button></td></tr>

    </form>
</table>
<jstl:forEach var="obj" items="${ErrorMessageList}">
    <p>${obj} </p>
</jstl:forEach>
</body>
</html>
