package org.example.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Service abstrait pour les appels API.
 * Gère les connexions HTTP et la désérialisation JSON avec Jackson.
 */
public class ApiService {
    /**
     * Clé API pour accéder aux services externes
     */
    protected String apiKey = "536b0c985ef3e28423d2b46f80a95da9";

    /**
     * Client HTTP Jersey pour les requêtes API
     */
    protected Client client = ClientBuilder.newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    /**
     * ObjectMapper Jackson configuré pour ignorer les propriétés inconnues
     */
    protected ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Récupère les données d'une URL et les désérialise en objet Java.
     * Les propriétés JSON non reconnues sont ignorées.
     *
     * @param urlString    l'URL complète de l'API
     * @param responseType la classe du type de réponse attendu
     * @param <T>          le type générique de la réponse
     * @return l'objet désérialisé du type T
     * @throws Exception si la réponse est vide ou en cas d'erreur de désérialisation
     */
    protected <T> T fetchFromUrl(String urlString, Class<T> responseType) throws Exception {
        try {
            String response = client.target(urlString)
                    .request()
                    .get(String.class);

            if (response == null || response.isEmpty()) {
                throw new Exception("Réponse vide de l'API");
            }

            return objectMapper.readValue(response, responseType);
        } catch (Exception e) {
            throw new Exception("Erreur API: " + e.getMessage(), e);
        }
    }
}
