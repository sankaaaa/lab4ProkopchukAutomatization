package org.sasha.service;


import org.json.JSONObject;
import org.sasha.Forecast;
import org.sasha.annotation.CheckCountryCapital;
import org.sasha.annotation.ForecastChecking;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


@ForecastChecking
public class ForecastCheckingProc implements ForecastList {
    private static final String URL = "https://api.weatherapi.com/v1/forecast.json?key=%s&q=%s&days=1";
    private static final String KEY = "addab5935ca14383b6e221323230411";

    public Forecast getWeather(@CheckCountryCapital String city) throws IOException {
        String urlString = String.format(URL, KEY, city);
        URL link = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        connection.setRequestMethod("GET");

        Scanner sc = new Scanner(connection.getInputStream());
        String response = sc.useDelimiter("\\A").next();

        JSONObject jO = new JSONObject(response);
        String description = jO.getJSONObject("current").getJSONObject("condition").getString("text");
        double tempC = jO.getJSONObject("current").getDouble("temp_c");
        double tempF = jO.getJSONObject("current").getDouble("temp_f");
        String lastUpdate = jO.getJSONObject("current").getString("last_updated");

        return new Forecast(city, tempC, tempF, description, lastUpdate);
    }
}
