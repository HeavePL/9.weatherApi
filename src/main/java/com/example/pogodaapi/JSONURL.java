package com.example.pogodaapi;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONURL {
    private JSONObject jOAll;
    private JSONObject main;
    private int visibility;
    private JSONObject wind;
    private JSONObject rain;
    private JSONObject snow;
    private JSONObject clouds;
    private String name;
    private String icon;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    private int responseCode;
    public JSONURL(String city){
        String keyAPI = "574868412a86f9e861f957ecc68a6807";
        String link = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+keyAPI+"&lang=pl&units=metric";
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
        setResponseCode(responseCode);
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
            // setting all data JSON
            setjOAll(new JSONObject(responseString));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("GET request did not work.");
            return;
        };
        JSONArray weatherArray = getjOAll().getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String iconString = weather.getString("icon");
        String urlIcon = "https://openweathermap.org/img/wn/"+iconString+"@2x.png";
        setIcon(urlIcon);
        setMain(getjOAll().getJSONObject("main"));
        setVisibility(getjOAll().getInt("visibility"));
        setWind(getjOAll().getJSONObject("wind"));
        if (getjOAll().has("rain")) {
            setRain(getjOAll().getJSONObject("rain"));
        }
        if (getjOAll().has("snow")){
            setSnow(getjOAll().getJSONObject("snow"));
        }
        setClouds(getjOAll().getJSONObject("clouds"));
        setName(getjOAll().getString("name"));
    }

    public void setjOAll(JSONObject jOAll) {
        this.jOAll = jOAll;
    }

    public JSONObject getjOAll() {
        return jOAll;
    }

    public void setClouds(JSONObject clouds) {
        this.clouds = clouds;
    }

    public JSONObject getClouds() {
        return clouds;
    }

    public void setMain(JSONObject main) {
        this.main = main;
    }

    public JSONObject getMain() {
        return main;
    }

    public void setRain(JSONObject rain) {
        this.rain = rain;
    }

    public JSONObject getRain() {
        return rain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSnow(JSONObject snow) {
        this.snow = snow;
    }

    public JSONObject getSnow() {
        return snow;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setWind(JSONObject wind) {
        this.wind = wind;
    }

    public JSONObject getWind() {
        return wind;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
