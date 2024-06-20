package org.sasha;


public class Forecast {
    private String city;
    private double tempC;
    private double tempF;
    private String description;

    private String lastUpdate;

    public Forecast(String city, double temperature, double tempF, String description, String lastUpdate) {
        this.city = city;
        this.tempC = temperature;
        this.tempF = tempF;
        this.description = description;
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public double getTempC() {
        return tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
