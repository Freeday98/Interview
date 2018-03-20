package dao.logic;

import dao.entity.EmployeeEntity;
import exception.DataBaseException;
import util.EmployeeEntityCreator;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends AbstractDBWorker<EmployeeEntity> {
    private static final String GET_LIST = "SELECT * FROM employee WHERE departmentEmployee = ?";
    private static final String GET_ENTITY = "SELECT * FROM employee WHERE  idEmployee = ?";
    private static final String DELETE_ENTITY = "DELETE  FROM  employee WHERE  idEmployee = ?";
    private static final String SAVE_ENTITY = " INSERT INTO employee(firstNameEmployee, secondNameEmployee, contactMailEmployee, salaryEmployee, birthDateEmployee, departmentEmployee) " +
            "VALUES(?,?,?,?,?,?)";
    private static final String GET_ENTITY_BY_NAME = "SELECT * FROM employee WHERE  contactMailEmployee = ?";
    private static final String UPDATE_ENTITY = "UPDATE  employee SET firstNameEmployee = ?, secondNameEmployee =?," +
            "contactMailEmployee =?, salaryEmployee =?,birthDateEmployee=?, departmentEmployee =? WHERE idEmployee = ?";
    public void saveEntity(EmployeeEntity employeeEntity) {

        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(SAVE_ENTITY,connection);) {
            statement.setString(1, employeeEntity.getFirstNameEmployee());
            statement.setString(2, employeeEntity.getSecondNameEmployee());
            statement.setString(3, employeeEntity.getContactMailEmployee());
            statement.setInt(4, employeeEntity.getSalaryEmployee());
            statement.setDate(5, employeeEntity.getBirthDateEmployee());
            statement.setInt(6, employeeEntity.getDepartmentEmployee());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
    }

    public EmployeeEntity getEntityByName(String name) {
        EmployeeEntity employeeEntity = null;
        try ( Connection connection =  openConnection(); PreparedStatement statement = getPreparedStatement(GET_ENTITY_BY_NAME,connection);) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            employeeEntity = EmployeeEntityCreator.createEntityFromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
        return employeeEntity;
    }

    public List<EmployeeEntity> getList(Integer id) {
        List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(GET_LIST,connection)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setIdEmployee(resultSet.getInt("idEmployee"));
                employeeEntity.setFirstNameEmployee(resultSet.getString("firstNameEmployee"));
                employeeEntity.setSecondNameEmployee(resultSet.getString("secondNameEmployee"));
                employeeEntity.setContactMailEmployee(resultSet.getString("contactMailEmployee"));
                employeeEntity.setSalaryEmployee(resultSet.getInt("salaryEmployee"));
                employeeEntity.setBirthDateEmployee(resultSet.getDate("birthDateEmployee"));
                employeeEntity.setDepartmentEmployee(resultSet.getInt("departmentEmployee"));
                System.out.println(employeeEntity.toString());
                list.add(employeeEntity);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
        return list;
    }

    public void deleteEntity(Integer id) {

        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(DELETE_ENTITY,connection);) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
    }

    public EmployeeEntity getEntity(Integer id) {
        EmployeeEntity employeeEntity = null;
        try (Connection connection = openConnection();PreparedStatement statement = getPreparedStatement(GET_ENTITY,connection);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            employeeEntity = EmployeeEntityCreator.createEntityFromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
        return employeeEntity;
    }

    public void updateEntity(EmployeeEntity employeeEntity, Integer id) {

        try(Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(UPDATE_ENTITY,connection);) {
            statement.setString(1, employeeEntity.getFirstNameEmployee());
            statement.setString(2, employeeEntity.getSecondNameEmployee());
            statement.setString(3, employeeEntity.getContactMailEmployee());
            statement.setInt(4, employeeEntity.getSalaryEmployee());
            statement.setDate(5, employeeEntity.getBirthDateEmployee());
            statement.setInt(6, employeeEntity.getDepartmentEmployee());
            statement.setInt(7,id);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
    }
}
