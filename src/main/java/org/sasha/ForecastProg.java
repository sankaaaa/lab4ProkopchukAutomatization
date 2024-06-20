package org.sasha;

import org.sasha.processor.CheckCapitalHandler;
import org.sasha.service.ForecastCheckingProc;
import org.sasha.service.ForecastCheckingProcFactory;
import org.sasha.service.ForecastList;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class ForecastProg {
    public static void main(String[] args) {
        ForecastList weatherService = (ForecastList) Proxy.newProxyInstance(
                ForecastCheckingProcFactory.class.getClassLoader(),
                new Class<?>[]{ForecastList.class},
                new CheckCapitalHandler(new ForecastCheckingProc())
        );

        try {
            Forecast weather = weatherService.getWeather("Germany");
            System.out.println("Country: " + weather.getCity() + ", " + "\nTemperature in Celsius: " +
                    weather.getTempC() + "°C, " + "\nTemperature in Fahrenheit: " + weather.getTempF() + "°F, " +
                    "\nWeather description: "+ weather.getDescription() + ", \nLast update: " + weather.getLastUpdate());

            weather = weatherService.getWeather("France");
            System.out.println("\nCountry: " + weather.getCity() + ", " + "\nTemperature in Celsius: " +
                    weather.getTempC() + "°C, " + "\nTemperature in Fahrenheit: " + weather.getTempF() + "°F, " +
                    "\nWeather description: "+ weather.getDescription() + ", \nLast update: " + weather.getLastUpdate());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            Forecast weather = weatherService.getWeather("Finland");
            System.out.println("\nCountry: " + weather.getCity() + ": " + weather.getTempC() + "°C, " + weather.getTempF() + "°F, " + weather.getDescription() + ", \nLast update: " + weather.getLastUpdate());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
