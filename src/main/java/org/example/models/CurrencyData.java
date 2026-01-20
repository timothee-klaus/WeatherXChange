package org.example.models;

import java.util.Map;

public class CurrencyData {
    private String baseCurrency;
    private Map<String, Double> rates;

    public CurrencyData(String baseCurrency, Map<String, Double> rates) {
        this.baseCurrency = baseCurrency;
        this.rates = rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public Double getRate(String currency) {
        return rates.get(currency);
    }
}

