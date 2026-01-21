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

    /**
     * Visibilité en mètres
     */
    private int visibility;

    /**
     * Données système (pays, lever/coucher du soleil)
     */
    private SysData sys;

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

    public int getVisibility() {
        return visibility;
    }

    public SysData getSys() {
        return sys;
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

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setSys(SysData sys) {
        this.sys = sys;
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
         * Température ressentie en Celsius
         */
        private double feels_like;

        /**
         * Pression atmosphérique en hPa
         */
        private int pressure;

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
         * Retourne la température ressentie
         * @return la température ressentie en Celsius
         */
        public double getFeelsLike() {
            return feels_like;
        }

        /**
         * Retourne la pression atmosphérique
         * @return la pression en hPa
         */
        public int getPressure() {
            return pressure;
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

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
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
         * Code de l'icône météo (ex: "01d", "02n")
         * Permet de construire l'URL de l'image via OpenWeatherMap
         */
        private String icon;

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

        /**
         * Retourne le code de l'icône
         * @return le code (ex: "01d")
         */
        public String getIcon() {
            return icon;
        }

        /**
         * Définit le code de l'icône
         * @param icon le code de l'icône
         */
        public void setIcon(String icon) {
            this.icon = icon;
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

    /**
     * Classe interne pour les données système (pays, etc.)
     */
    public static class SysData {
        /**
         * Code pays (ex: "FR", "BJ")
         */
        private String country;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}

