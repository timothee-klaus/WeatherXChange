package org.example.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.example.models.WeatherData;
import org.example.ui.MainFrame;
import java.awt.*;

/**
 * Panel d'affichage des données météo avec un design moderne.
 */
public class WeatherPanel extends JPanel {
    private JLabel cityLabel, tempLabel, descLabel, humidityLabel, windLabel;
    private JTextField searchField;
    private JButton searchButton;

    public WeatherPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(MainFrame.BACKGROUND_COLOR);
        setBorder(new EmptyBorder(25, 30, 25, 30));

        // Panel de recherche
        add(createSearchPanel(), BorderLayout.NORTH);

        // Card météo principale
        add(createWeatherCard(), BorderLayout.CENTER);
    }

    /**
     * Crée le panel de recherche de ville
     */
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("🔍 Rechercher une ville");
        searchLabel.setFont(MainFrame.HEADER_FONT);
        searchLabel.setForeground(MainFrame.TEXT_COLOR);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setOpaque(false);

        searchField = MainFrame.createStyledTextField();
        searchField.setPreferredSize(new Dimension(250, 45));

        searchButton = MainFrame.createStyledButton("Rechercher", MainFrame.PRIMARY_COLOR);

        inputPanel.add(searchField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);

        JPanel wrapper = new JPanel(new BorderLayout(0, 10));
        wrapper.setOpaque(false);
        wrapper.add(searchLabel, BorderLayout.NORTH);
        wrapper.add(inputPanel, BorderLayout.CENTER);

        return wrapper;
    }

    /**
     * Crée la carte d'affichage météo
     */
    private JPanel createWeatherCard() {
        JPanel card = MainFrame.createCard();
        card.setLayout(new BorderLayout(0, 20));

        // Titre de la carte
        JLabel cardTitle = new JLabel("☀ Météo actuelle");
        cardTitle.setFont(MainFrame.HEADER_FONT);
        cardTitle.setForeground(MainFrame.PRIMARY_COLOR);
        card.add(cardTitle, BorderLayout.NORTH);

        // Contenu météo
        JPanel contentPanel = new JPanel(new GridLayout(5, 1, 0, 15));
        contentPanel.setOpaque(false);

        cityLabel = createInfoLabel("🏙 Ville", "-");
        tempLabel = createInfoLabel("🌡 Température", "-");
        descLabel = createInfoLabel("📝 Description", "-");
        humidityLabel = createInfoLabel("💧 Humidité", "-");
        windLabel = createInfoLabel("💨 Vent", "-");

        contentPanel.add(cityLabel);
        contentPanel.add(tempLabel);
        contentPanel.add(descLabel);
        contentPanel.add(humidityLabel);
        contentPanel.add(windLabel);

        card.add(contentPanel, BorderLayout.CENTER);

        return card;
    }

    /**
     * Crée un label d'information stylisé
     */
    private JLabel createInfoLabel(String icon, String value) {
        JLabel label = new JLabel(icon + ": " + value);
        label.setFont(MainFrame.VALUE_FONT);
        label.setForeground(MainFrame.TEXT_COLOR);
        return label;
    }

    /**
     * Met à jour l'affichage avec les données météo
     */
    public void updateWeather(WeatherData data) {
        SwingUtilities.invokeLater(() -> {
            cityLabel.setText("🏙 Ville: " + data.getCity());
            tempLabel.setText(String.format("🌡 Température: %.1f°C", data.getTemperature()));
            descLabel.setText("📝 Description: " + capitalize(data.getDescription()));
            humidityLabel.setText(String.format("💧 Humidité: %.0f%%", data.getHumidity()));
            windLabel.setText(String.format("💨 Vent: %.1f m/s", data.getWindSpeed()));
        });
    }

    /**
     * Capitalise la première lettre
     */
    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    /**
     * Retourne le champ de recherche
     */
    public JTextField getSearchField() {
        return searchField;
    }

    /**
     * Retourne le bouton de recherche
     */
    public JButton getSearchButton() {
        return searchButton;
    }
}

