<%@ page import="java.util.List" %>
<%@ page import="dao.entity.DepartmentEntity" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.03.2018
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<%response.setCharacterEncoding("UTF-8"); %>
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <style>
        .layer1 {
            position: absolute;
            bottom: 50%;
            right: 55%;

        }
    </style>
</head>
<body>
<form>
    <table border="1" cellpadding="3" cellspacing="15" width="400" class="layer1">
        <tr>
            <td>Название отдела</td>
            <td>Количество сотрудников</td>
        </tr>
        <jstl:forEach var="obj" items="${ListOfDepartments}">
            <tr>
                <td>${obj.nameDepartment}</td>
                <td>${obj.numOfEmployee}</td>
                <td>
                    <button class="btn btn-primary" type="submit" formaction="/DepartmentDelete"
                            value="${obj.idDepartment}" name="id" formmethod="post">Delete
                    </button>
                </td>
                <td>
                    <button class="btn btn-primary" type="submit" formaction="/DepartmentEdit"
                            value="${obj.idDepartment}" name="id" formmethod="get">Edit
                    </button>
                </td>
                <td>
                    <button class="btn btn-primary" type="submit" formaction="/EmployeeList" value="${obj.idDepartment}"
                            name="id" formmethod="get">List of employee
                    </button>
                </td>
            </tr>
        </jstl:forEach>
        <tr>
            <td></td>
            <td>
                <button class="btn btn-primary" type="submit" formaction="/DepartmentCreate" formmethod="get">New
                    Department
                </button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
