package dao.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDBWorker<K> {
    final String URL = "jdbc:mysql://localhost:3306/testprojectdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final String password = "RevanBastila9889";
    final String user = "root";

    public AbstractDBWorker() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    PreparedStatement getPreparedStatement(String SQL,Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    public abstract void deleteEntity(Integer id);

    public abstract K getEntity(Integer id);

    public abstract void saveEntity(K entity);

    public abstract void updateEntity(K entity, Integer id);

    public abstract K getEntityByName(String name);


}
