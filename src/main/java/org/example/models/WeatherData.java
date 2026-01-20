package org.example.models;

public class WeatherData {
    private String city;
    private double temperature;
    private String description;
    private double humidity;
    private double windSpeed;

    public WeatherData(String city, double temperature, String description, double humidity, double windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}

