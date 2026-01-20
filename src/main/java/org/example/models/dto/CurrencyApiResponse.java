package org.example.models.dto;

import java.util.Map;

/**
 * DTO pour mapper la réponse JSON de l'API ExchangeRate.
 * Jackson utilise les noms de champs qui correspondent aux clés JSON.
 */
public class CurrencyApiResponse {
    /**
     * Devise de base (correspond à "base" dans le JSON)
     */
    private String base;

    /**
     * Carte des taux de change (correspond à "rates" dans le JSON)
     */
    private Map<String, Double> rates;

    /**
     * Retourne la devise de base
     *
     * @return la devise de base (ex: "EUR", "USD")
     */
    public String getBase() {
        return base;
    }

    /**
     * Retourne les taux de change
     *
     * @return une carte avec les devises et leurs taux
     */
    public Map<String, Double> getRates() {
        return rates;
    }

    /**
     * Définit la devise de base
     *
     * @param base la devise de base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Définit les taux de change
     *
     * @param rates la carte des taux
     */
    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}

