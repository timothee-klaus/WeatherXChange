package org.example.models.dto;

import java.util.List;

/**
 * DTO pour mapper la réponse JSON de l'API OpenWeatherMap.
 * Jackson utilise les noms de champs qui correspondent aux clés JSON.
 */
public class WeatherApiResponse {
    /**
     * Nom de la ville (correspond à "name" dans le JSON)
     */
    private String name;

    /**
     * Données principales (température, humidité)
     */
    private MainData main;

    /**
     * Liste des descriptions météo
     */
    private List<WeatherDescription> weather;

    /**
     * Données du vent
     */
    private WindData wind;

    // Getters

    /**
     * Retourne le nom de la ville
     *
     * @return le nom de la ville
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne les données principales (température, humidité)
     *
     * @return MainData contenant temp et humidity
     */
    public MainData getMain() {
        return main;
    }

    /**
     * Retourne la liste des descriptions météo
     *
     * @return liste de WeatherDescription
     */
    public List<WeatherDescription> getWeather() {
        return weather;
    }

    /**
     * Retourne les données du vent
     *
     * @return WindData contenant la vitesse
     */
    public WindData getWind() {
        return wind;
    }

    // Setters

    /**
     * Définit le nom de la ville
     *
     * @param name le nom de la ville
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit les données principales
     *
     * @param main les données principales
     */
    public void setMain(MainData main) {
        this.main = main;
    }

    /**
     * Définit la liste des descriptions météo
     *
     * @param weather la liste des descriptions
     */
    public void setWeather(List<WeatherDescription> weather) {
        this.weather = weather;
    }

    /**
     * Définit les données du vent
     *
     * @param wind les données du vent
     */
    public void setWind(WindData wind) {
        this.wind = wind;
    }

    /**
     * Classe interne pour les données principales
     */
    public static class MainData {
        /**
         * Température en Celsius (correspond à "temp" dans le JSON)
         */
        private double temp;

        /**
         * Humidité en pourcentage (correspond à "humidity" dans le JSON)
         */
        private double humidity;

        /**
         * Retourne la température
         *
         * @return la température en Celsius
         */
        public double getTemp() {
            return temp;
        }

        /**
         * Retourne l'humidité
         *
         * @return l'humidité en pourcentage
         */
        public double getHumidity() {
            return humidity;
        }

        /**
         * Définit la température
         *
         * @param temp la température en Celsius
         */
        public void setTemp(double temp) {
            this.temp = temp;
        }

        /**
         * Définit l'humidité
         *
         * @param humidity l'humidité en pourcentage
         */
        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }
    }

    /**
     * Classe interne pour les descriptions météo
     */
    public static class WeatherDescription {
        /**
         * Description de la météo (correspond à "description" dans le JSON)
         */
        private String description;

        /**
         * Retourne la description météo
         *
         * @return la description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Définit la description météo
         *
         * @param description la description
         */
        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * Classe interne pour les données du vent
     */
    public static class WindData {
        /**
         * Vitesse du vent en m/s (correspond à "speed" dans le JSON)
         */
        private double speed;

        /**
         * Retourne la vitesse du vent
         *
         * @return la vitesse en m/s
         */
        public double getSpeed() {
            return speed;
        }

        /**
         * Définit la vitesse du vent
         *
         * @param speed la vitesse en m/s
         */
        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}

