package com;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearch {

    public static void search(String term) throws IOException {
        String url = "https://www.google.com/search" + "?q=" + term;
        int k =0;
        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("cite");
        Set<Element> set = new HashSet<>(links);
        for (Element link : set) {
            String text = link.text();
            if (text.contains("›")) {
                text = text.replaceAll(" › ", "/");
            }
            System.out.println(text);
            k++;
            if (k >= 10) break;

        }
    }
}