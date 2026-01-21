package org.example.models;

/**
 * Modèle de données météo enrichi contenant toutes les informations
 * utiles retournées par l'API OpenWeatherMap.
 */
public class WeatherData {
    private String city;
    private String country;
    private double temperature;
    private double feelsLike;
    private String description;
    private String iconCode;
    private double humidity;
    private double windSpeed;
    private int pressure;
    private int visibility;

    public WeatherData(String city, String country, double temperature, double feelsLike,
                       String description, String iconCode, double humidity,
                       double windSpeed, int pressure, int visibility) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.description = description;
        this.iconCode = iconCode;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.visibility = visibility;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Retourne le code de l'icône météo.
     * Utiliser getIconUrl() pour obtenir l'URL complète de l'image.
     */
    public String getIconCode() {
        return iconCode;
    }

    /**
     * Construit l'URL de l'icône météo depuis le code retourné par l'API.
     * Format: https://openweathermap.org/img/wn/{code}@2x.png
     */
    public String getIconUrl() {
        return "https://openweathermap.org/img/wn/" + iconCode + "@2x.png";
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    /**
     * Retourne la visibilité en kilomètres (convertie depuis les mètres).
     */
    public double getVisibilityKm() {
        return visibility / 1000.0;
    }
}

