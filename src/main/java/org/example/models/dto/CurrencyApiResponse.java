package org.example.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * DTO pour mapper la réponse JSON de l'API ExchangeRate v6.
 * Utilise les noms de propriétés JSON correspondant à la nouvelle API.
 */
public class CurrencyApiResponse {

    /**
     * Code de la devise de base (correspond à "base_code" dans le JSON)
     */
    @JsonProperty("base_code")
    private String baseCode;

    /**
     * Carte des taux de conversion (correspond à "conversion_rates" dans le JSON)
     */
    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;

    /**
     * Retourne le code de la devise de base
     */
    public String getBaseCode() {
        return baseCode;
    }

    /**
     * Retourne les taux de conversion
     */
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    /**
     * Définit le code de la devise de base
     */
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    /**
     * Définit les taux de conversion
     */
    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}

