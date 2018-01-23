package com.andre.worke;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertBase {
    private int id = 1;
    private Connection co;
    public InsertBase(Connection co) {
        this.co = co;


    }

    public void insert (String url, String img, String param, String info){
        try {
            Statement statement = co.createStatement();
            statement.executeUpdate("INSERT INTO dogs " +
                    "VALUES ("+id+"," +
                    "'"+url+"'," +
                    "'"+img+"'," +
                    "'"+param+"'," +
                    "'"+info+"');");
            id++;
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
