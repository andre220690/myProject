package com.andre.worke;


import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        ConnectBase connect = new ConnectBase();
        connect.open();

        InsertBase insertBase = new InsertBase(connect.getCo());

        DownladSummary sum = new DownladSummary(insertBase);
        sum.start("https://www.avito.ru/ekaterinburg/sobaki");
        System.out.println(sum.getList().size());

        for(String url : sum.getList()){
            sum.savePage("https://www.avito.ru"+url);
        }

        connect.close();
    }
}
