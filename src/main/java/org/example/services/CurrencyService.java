package org.example.services;

import org.example.models.CurrencyData;
import org.example.models.dto.CurrencyApiResponse;

/**
 * Service pour récupérer les taux de change via l'API ExchangeRate v6.
 */
public class CurrencyService extends ApiService {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String ENDPOINT = "/latest/";

    /**
     * Récupère les taux de change pour une devise de base donnée.
     *
     * @param baseCurrency le code de la devise de base (ex: "USD", "EUR")
     * @return les données de conversion de devises
     * @throws Exception si l'appel API échoue
     */
    public CurrencyData getExchangeRates(String baseCurrency) throws Exception {
        String urlString = BASE_URL + CurrencyApiKey + ENDPOINT + baseCurrency;
        CurrencyApiResponse response = fetchFromUrl(urlString, CurrencyApiResponse.class);
        return new CurrencyData(response.getBaseCode(), response.getConversionRates());
    }
}

