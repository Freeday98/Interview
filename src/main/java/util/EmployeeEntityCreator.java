package util;

import dao.entity.EmployeeEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeEntityCreator {
    public static EmployeeEntity createEntityFromRequest(HttpServletRequest req) {
        EmployeeEntity employeeEntity = null;
        java.sql.Date sqlDate = null;
        Integer id = null;
        try {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(req.getParameter("birthDateEmployee"));
            sqlDate = new java.sql.Date(date.getTime() + 86400000);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstNameEmployee(req.getParameter("EmployeeFirstName"));
        employeeEntity.setSecondNameEmployee(req.getParameter("EmployeeSecondName"));
        employeeEntity.setContactMailEmployee(req.getParameter("EmployeeContactMail"));
        employeeEntity.setSalaryEmployee(Integer.parseInt(req.getParameter("salaryEmployee")));
        employeeEntity.setBirthDateEmployee(sqlDate);
        employeeEntity.setDepartmentEmployee(Integer.parseInt(req.getParameter("department")));
        String parameter = req.getParameter("unique");
        if (parameter != null) {
            id = Integer.parseInt(parameter);
            employeeEntity.setIdEmployee(id);
        }
        return employeeEntity;
    }

    public static EmployeeEntity createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        EmployeeEntity employeeEntity = null;
        if (resultSet.next()) {
            employeeEntity = new EmployeeEntity();
            employeeEntity.setIdEmployee(resultSet.getInt("idEmployee"));
            employeeEntity.setFirstNameEmployee(resultSet.getString("firstNameEmployee"));
            employeeEntity.setSecondNameEmployee(resultSet.getString("secondNameEmployee"));
            employeeEntity.setContactMailEmployee(resultSet.getString("contactMailEmployee"));
            employeeEntity.setSalaryEmployee(resultSet.getInt("salaryEmployee"));
            employeeEntity.setBirthDateEmployee(resultSet.getDate("birthDateEmployee"));
            employeeEntity.setDepartmentEmployee(resultSet.getInt("departmentEmployee"));
        }
        return employeeEntity;
    }
}
