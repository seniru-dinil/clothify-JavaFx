package edu.icet.clothify.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;

    private DataBaseConnection() throws SQLException {
        String username="root";
        String password="1234";
        String url="jdbc:mysql://localhost:3306/";
        connection = DriverManager.getConnection(username, password, url);
    }

    public static DataBaseConnection getInstance() throws SQLException {
        return instance==null?instance=new DataBaseConnection() : instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
