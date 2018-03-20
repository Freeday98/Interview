<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.03.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="dao.entity.EmployeeEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setCharacterEncoding("UTF-8"); %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title> Список сотрудников </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<form>
    <table border="1" cellpadding="2" cellspacing="2">
        <tr>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Мыло</td>
            <td>З/П</td>
            <td>Дата рождения</td>
        </tr>
        <jstl:forEach var="employee" items="${ListOfEmployee}">
            <tr>
                <td> ${employee.firstNameEmployee} </td>
                <td> ${employee.secondNameEmployee} </td>
                <td> ${employee.contactMailEmployee}</td>
                <td>${employee.salaryEmployee}</td>
                <td> ${employee.birthDateEmployee}</td>
                <td>
                    <button class="btn btn-primary" type="submit" name="unique" value="${employee.idEmployee}"
                            formmethod="post" formaction="/EmployeeDelete">Delete
                    </button>
                </td>
                <td>
                    <button class="btn btn-primary" type="submit" name="unique" value="${employee.idEmployee}"
                            formaction="/EmployeeEdit" formmethod="get">Edit
                    </button>
                </td>
            </tr>
        </jstl:forEach>
        <tr>
            <td>
                <button class="btn btn-primary" formaction="/Start" type="submit">To departments</button>
            </td>
            <td>
                <button class="btn btn-primary" type="submit" formaction="/EmployeeCreate" formmethod="get">Add</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
