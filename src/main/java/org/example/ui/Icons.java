package org.example.ui;

/**
 * Classe utilitaire pour les icônes de l'application.
 * Utilise des caractères Unicode compatibles avec toutes les polices.
 */
public final class Icons {
    
    // Météo
    public static final String WEATHER_SUN = "\u2600";      // ☀
    public static final String WEATHER_CLOUD = "\u2601";    // ☁
    public static final String WEATHER_RAIN = "\u2602";     // ☂
    
    // Navigation et actions
    public static final String SEARCH = "\u2315";           // ⌕
    public static final String REFRESH = "\u21BB";          // ↻
    public static final String CHECK = "\u2713";            // ✓
    
    // Données météo
    public static final String LOCATION = "\u25C9";         // ◉
    public static final String TEMPERATURE = "\u2103";      // ℃
    public static final String HUMIDITY = "\u2248";         // ≈ (représente l'eau)
    public static final String WIND = "\u2192";             // →
    
    // Devises
    public static final String CURRENCY = "\u00A4";         // ¤
    public static final String ARROW_RIGHT = "\u2192";      // →
    public static final String ARROW_LEFT = "\u2190";       // ←
    public static final String EXCHANGE = "\u21C4";         // ⇄
    
    // Texte alternatif si les icônes ne fonctionnent pas
    public static final String TEXT_CITY = "[Ville]";
    public static final String TEXT_TEMP = "[Temp]";
    public static final String TEXT_HUMIDITY = "[Humid]";
    public static final String TEXT_WIND = "[Vent]";
    public static final String TEXT_CONVERT = "[Conv]";
    
    private Icons() {}
}

