package com.andre.worke;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	// write your code here
        ConnectBase connect = new ConnectBase();
        connect.open();

        InsertBase insertBase = new InsertBase(connect.getCo());

        DownladSummary sum = new DownladSummary(insertBase);
        sum.start("https://www.avito.ru/ekaterinburg/sobaki");
        System.out.println(sum.list.size());

        for(String url : sum.list){
            sum.savePage("https://www.avito.ru"+url);
        }

        connect.close();
    }
}
