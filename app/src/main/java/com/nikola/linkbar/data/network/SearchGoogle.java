package com.nikola.linkbar.data.network;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class SearchGoogle {
    private String desc;
    private String query;
    private String url;


    public SearchGoogle(String desc) {
        this.desc = desc;
    }

    public void Search(){
        try {
            query = URLEncoder.encode(desc,"utf-8");
            Inrent
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
