package com;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleSearch {

    public static void search(String term) throws GeneralSecurityException, IOException {

        String cx = "002845322276752338984:vxqzfa86nqc"; //search engine

        //Instance Customsearch
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
            .setApplicationName("MyApplication")
            .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyCxE7Eoq6Qb4teiIM41sq7R_o-s0CixSL0"))
            .build();

        //Set search parameter
        Customsearch.Cse.List list = cs.cse().list(term).setCx(cx);

        //Execute search
        Search result = list.execute();
        if (result.getItems()!=null){
            for (Result ri : result.getItems()) {
                //Get title and link
                System.out.println(ri.getTitle() + ", " + ri.getLink());
            }
        }

    }

}