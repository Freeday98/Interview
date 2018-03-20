<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.03.2018
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание нового пользователя</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<form action="/EmployeeCreate" method="post">
    <table cellpadding="2" cellspacing="2" border="1">
        <tr>
            <td>Имя</td>
            <td>Фамилия</td>
            <td> E-mail</td>
            <td>Зарплата</td>
            <td>Дата рождения</td>
            <td>Отдел</td>
        </tr>
        <tr>
            <td><input type="text" name="EmployeeFirstName" value="${requestScope.WrongValue.firstNameEmployee}"></td>
            <td><input type="text" name="EmployeeSecondName" value="${requestScope.WrongValue.secondNameEmployee}"></td>
            <td><input type="text" name="EmployeeContactMail" value="${requestScope.WrongValue.contactMailEmployee}">
            </td>
            <td><input type="text" name="salaryEmployee" value="${requestScope.WrongValue.salaryEmployee}"></td>
            <td><input type="text" name="birthDateEmployee" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
                       placeholder="YYYY-MM-DD" value="${requestScope.WrongValue.birthDateEmployee}"></td>
            <td><select name="department">
                <jstl:forEach var="item" items="${ListOfDepartments}">
                    <option value="${item.idDepartment}"> ${item.nameDepartment}</option>
                </jstl:forEach>
            </select></td>
        </tr>
        <tr>
<jstl:if test="${requestScope.id==null}">
            <td>
                <button class="btn btn-primary">New Employee</button>
            </td>
</jstl:if>
            <jstl:if test="${requestScope.id!=null}">
            <td>
                <button class="btn btn-primary" formaction="/EmployeeEdit?unique=${requestScope.id}" formmethod="post">
                    Edit employee
                </button>
            </td>
            </jstl:if>
        </tr>
    </table>
    <jstl:forEach var="obj" items="${ErrorMessageList}">
        <p>${obj} </p>
    </jstl:forEach>
</form>
</body>
</html>
