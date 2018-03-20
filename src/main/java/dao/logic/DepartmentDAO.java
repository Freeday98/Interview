package dao.logic;

import dao.entity.DepartmentEntity;
import exception.DataBaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends AbstractDBWorker<DepartmentEntity> {
    private static final String GET_LIST = "SELECT * FROM  department";
    private static final String GET_ENTITY = "SELECT * FROM  department WHERE idDepartment = ?";
    private static final String DELETE_ENTITY = "DELETE  FROM department WHERE idDepartment = ?";
    private static final String SAVE_ENTITY = " INSERT INTO department(nameDepartment, numOfEmployees) VALUES(?,?)";
    private static final String GET_ENTITY_BY_NAME = "SELECT * FROM  department WHERE nameDepartment = ?";
    private static final String UPDATE_ENTITY = "UPDATE department SET nameDepartment = ? WHERE idDepartment = ?";

    public List<DepartmentEntity> getList() {
        List<DepartmentEntity> list = new ArrayList<DepartmentEntity>();

        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(GET_LIST, connection)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setIdDepartment(set.getInt("idDepartment"));
                departmentEntity.setNameDepartment(set.getString("nameDepartment"));
                departmentEntity.setNumOfEmployee(set.getInt("numOfEmployees"));
                list.add(departmentEntity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
        return list;
    }

    public void saveEntity(DepartmentEntity entity) {

        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(SAVE_ENTITY, connection)) {
            statement.setString(1, entity.getNameDepartment());
            statement.setInt(2, entity.getNumOfEmployee());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
    }

    public void deleteEntity(Integer id) {
        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(DELETE_ENTITY, connection)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
    }


    public DepartmentEntity getEntity(Integer id) {
        DepartmentEntity obj = new DepartmentEntity();
        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(GET_ENTITY, connection)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            resultSet.first();
            while (resultSet.next()) {
                obj.setIdDepartment(resultSet.getInt("idDepartment"));
                obj.setNameDepartment(resultSet.getString("nameDepartment"));
                obj.setNumOfEmployee(resultSet.getInt("numOfEmployees"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
        return obj;
    }

    public void updateEntity(DepartmentEntity entity, Integer id) {

        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(UPDATE_ENTITY, connection)) {
            statement.setString(1, entity.getNameDepartment());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException ex) {
            throw new DataBaseException(ex);
        }
    }

    public DepartmentEntity getEntityByName(String name) {
        DepartmentEntity obj = null;
        try (Connection connection = openConnection(); PreparedStatement statement = getPreparedStatement(GET_ENTITY_BY_NAME, connection)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                obj = new DepartmentEntity();
                obj.setIdDepartment(resultSet.getInt("idDepartment"));
                obj.setNameDepartment(resultSet.getString("nameDepartment"));
                obj.setNumOfEmployee(resultSet.getInt("numOfEmployees"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBaseException(ex);
        }
        return obj;
    }

}


