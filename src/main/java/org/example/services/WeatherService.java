package org.example.services;

import org.example.models.WeatherData;
import org.example.models.dto.WeatherApiResponse;

public class WeatherService extends ApiService {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherData getWeatherByCity(String city) throws Exception {
        String urlString = String.format("%s?q=%s&appid=%s&units=metric&lang=fr", BASE_URL, city, apiKey);

        WeatherApiResponse response = fetchFromUrl(urlString, WeatherApiResponse.class);

        return new WeatherData(
            response.getName(),
            response.getMain().getTemp(),
            response.getWeather().get(0).getDescription(),
            response.getMain().getHumidity(),
            response.getWind().getSpeed()
        );
    }
}

