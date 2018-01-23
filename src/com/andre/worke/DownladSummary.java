package com.andre.worke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class DownladSummary {
    private ArrayList<String> list=new ArrayList<>();
    private Document doc = null;
    private String url;
    private InsertBase insertBase;

    public ArrayList<String> getList() {
        return list;
    }

    public DownladSummary(InsertBase insertBase) {
        this.insertBase = insertBase;
    }

    public void start(String ur) throws IOException, InterruptedException {

        this.url = ur;

        while (true){
            new Thread() {
                public void run() {
                    try {
                        doc = Jsoup.connect(url).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(url);
                }
            }.run();
            sleep(5000);

            Elements els = doc.getElementsByClass("c-b-0");
            for (Element el : els) {
                list.add(el.getElementsByClass("item-description-title-link").first().attr("href"));
                System.out.println(list.get(list.size()-1));
            }
            try {
                url = "https://www.avito.ru" + doc.getElementsByClass("js-pagination-next").first().attr("href");
            } catch (Exception e){
                break;
            }

        }

    }

    public void savePage (String ur) throws InterruptedException {
        String e1, e2, e3;
        this.url=ur;
        new Thread() {
            public void run() {
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(url);
            }
        }.run();
        sleep(5000);

        try{
            e1 = doc.getElementsByClass("gallery-img-cover").get(0).attr("style");
            String s = e1.split("//")[1];
            e1=s.substring(0, s.length()-2);
        } catch (Exception e){
            e1="none";
        }
        try{
            e2 = doc.getElementsByClass("item-params").get(0).getElementsByTag("span").get(0).text();
        } catch (Exception e){
            e2 = "none";
        }
        try{
            e3 = doc.getElementsByClass("item-description-text").get(0).text();
        } catch (Exception e){
            e3 = "none";
        }

        insertBase.insert(url, e1, e2, e3);
    }

}



