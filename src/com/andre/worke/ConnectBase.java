package com.andre.worke;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBase {

    private Connection co;

    public Connection getCo() {
        return co;
    }

    public void open() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try{
            co = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1");
            Statement statement = co.createStatement();
            statement.executeUpdate("USE summary;");
            System.out.println("Connection!");
        }catch (Exception e){
            System.out.println();
        }
    }

    public void close() throws SQLException {
        co.close();
    }
}
