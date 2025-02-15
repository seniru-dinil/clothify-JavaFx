package edu.icet.clothify.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;
    private DBConnection() throws SQLException {
        String url ="jdbc:mysql://localhost:3306/clothify";
        String password="1234";
        String username = "root";

        connection= DriverManager.getConnection(url, username, password);
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance==null)instance=new DBConnection();
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

}
