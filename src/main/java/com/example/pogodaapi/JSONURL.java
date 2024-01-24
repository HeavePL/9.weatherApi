package com.example.pogodaapi;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONURL {
    public JSONURL(String city){
        String keyAPI = "574868412a86f9e861f957ecc68a6807";
        String link = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+keyAPI+"&lang=pl&units=metric";
        JSONObject jOAll;
        JSONObject main;
        int visibility;
        JSONObject wind;
        JSONObject rain;
        JSONObject snow;
        JSONObject clouds;
        String name;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpsURLConnection con = null;
        int responseCode = 0;
        try {
            con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            responseCode = con.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String responseString = response.toString();
            jOAll = new JSONObject(responseString);
            main = jOAll.getJSONObject("main");
            visibility = jOAll.getInt("visibility");
            wind = jOAll.getJSONObject("wind");


            rain = jOAll.getJSONObject("rain");
            snow = jOAll.getJSONObject("snow");
            clouds = jOAll.getJSONObject("clouds");
            name = jOAll.getString("name");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("GET request did not work.");
        };

    }
}
