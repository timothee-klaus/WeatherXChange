package org.example.services;

import org.example.models.CurrencyData;
import org.example.models.dto.CurrencyApiResponse;

public class CurrencyService extends ApiService {
    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public CurrencyData getExchangeRates(String baseCurrency) throws Exception {
        String urlString = BASE_URL + baseCurrency;

        CurrencyApiResponse response = fetchFromUrl(urlString, CurrencyApiResponse.class);

        return new CurrencyData(response.getBase(), response.getRates());
    }
}

