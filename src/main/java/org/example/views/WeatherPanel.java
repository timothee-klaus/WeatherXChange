package org.example.views;
import org.example.models.WeatherData;
import org.example.ui.Icons;
import org.example.ui.Theme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Panel d'affichage des informations météo.
 * Affiche l'icône météo récupérée depuis OpenWeatherMap,
 * ainsi que les détails (humidité, vent, pression, visibilité).
 */
public class WeatherPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JLabel cityLabel;
    private JLabel tempLabel;
    private JLabel feelsLikeLabel;
    private JLabel descLabel;
    private JLabel weatherIconLabel;
    private JLabel humidityValue;
    private JLabel windValue;
    private JLabel pressureValue;
    private JLabel visibilityValue;

    public WeatherPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));
        add(createSearchSection(), BorderLayout.NORTH);
        add(createWeatherCard(), BorderLayout.CENTER);
    }

    private JPanel createSearchSection() {

        JPanel section = new JPanel(new BorderLayout(0, 10));
        section.setOpaque(false);

        JLabel title = new JLabel(Icons.SEARCH + " Rechercher une ville");
        title.setFont(Theme.HEADER);
        title.setForeground(Theme.TEXT_DARK);

        JPanel inputRow = new JPanel(new BorderLayout(10, 0));
        inputRow.setOpaque(false);
        searchField = new JTextField();
        searchField.setFont(Theme.BODY_BOLD);
        searchField.setForeground(Theme.TEXT_DARK);
        searchField.setBackground(Color.WHITE);
        searchField.setCaretColor(Theme.TEXT_DARK);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER, 1),
            new EmptyBorder(10, 15, 10, 15)
        ));

        searchButton = Theme.createButton("Rechercher", Theme.PRIMARY);
        inputRow.add(searchField, BorderLayout.CENTER);
        inputRow.add(searchButton, BorderLayout.EAST);

        section.add(title, BorderLayout.NORTH);
        section.add(inputRow, BorderLayout.CENTER);

        return section;
    }

    private JPanel createWeatherCard() {
        JPanel card = Theme.createCard();
        card.setLayout(new BorderLayout(0, 30));
        card.add(createMainWeatherSection(), BorderLayout.CENTER);
        card.add(createDetailsGrid(), BorderLayout.SOUTH);
        return card;
    }

    private JPanel createMainWeatherSection() {
        JPanel main = new JPanel(new BorderLayout(20, 0));
        main.setOpaque(false);

        // Icône météo à gauche
        weatherIconLabel = new JLabel();
        weatherIconLabel.setPreferredSize(new Dimension(100, 100));
        weatherIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Infos principales au centre
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        cityLabel = new JLabel("--");
        cityLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        cityLabel.setForeground(Theme.PRIMARY);
        cityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        tempLabel = new JLabel("--°C");
        tempLabel.setFont(new Font("Segoe UI", Font.BOLD, 56));
        tempLabel.setForeground(Theme.TEXT_DARK);
        tempLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        feelsLikeLabel = new JLabel("Ressenti: --°C");
        feelsLikeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        feelsLikeLabel.setForeground(Theme.TEXT_LIGHT);
        feelsLikeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        descLabel = new JLabel("--");
        descLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        descLabel.setForeground(Theme.TEXT_LIGHT);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(cityLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(tempLabel);
        infoPanel.add(feelsLikeLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(descLabel);

        main.add(weatherIconLabel, BorderLayout.WEST);
        main.add(infoPanel, BorderLayout.CENTER);

        return main;
    }

    private JPanel createDetailsGrid() {
        JPanel grid = new JPanel(new GridLayout(2, 2, 15, 15));
        grid.setOpaque(false);

        humidityValue = createValueLabel("--%");
        windValue = createValueLabel("-- m/s");
        pressureValue = createValueLabel("-- hPa");
        visibilityValue = createValueLabel("-- km");

        grid.add(createDetailCard(Icons.HUMIDITY, "Humidité", humidityValue));
        grid.add(createDetailCard(Icons.WIND, "Vent", windValue));
        grid.add(createDetailCard("⏱", "Pression", pressureValue));
        grid.add(createDetailCard("👁", "Visibilité", visibilityValue));

        return grid;
    }

    private JPanel createDetailCard(String icon, String label, JLabel valueLabel) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Theme.BACKGROUND);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER, 1),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel iconLabel = new JLabel(icon + " " + label);
        iconLabel.setFont(Theme.BODY);
        iconLabel.setForeground(Theme.TEXT_LIGHT);
        iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(valueLabel);

        return card;
    }

    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Theme.TEXT_DARK);
        return label;
    }

    /**
     * Met à jour l'affichage avec les données météo.
     * Charge l'icône météo de manière asynchrone pour ne pas bloquer l'UI.
     */
    public void updateWeather(WeatherData data) {
        SwingUtilities.invokeLater(() -> {
            cityLabel.setText(Icons.LOCATION + " " + data.getCity() + ", " + data.getCountry());
            tempLabel.setText(String.format("%.0f°C", data.getTemperature()));
            feelsLikeLabel.setText(String.format("Ressenti: %.0f°C", data.getFeelsLike()));
            descLabel.setText(capitalize(data.getDescription()));
            humidityValue.setText(String.format("%.0f%%", data.getHumidity()));
            windValue.setText(String.format("%.1f m/s", data.getWindSpeed()));
            pressureValue.setText(String.format("%d hPa", data.getPressure()));
            visibilityValue.setText(String.format("%.1f km", data.getVisibilityKm()));

            // Chargement asynchrone de l'icône météo
            loadWeatherIcon(data.getIconUrl());
        });
    }

    /**
     * Charge l'icône météo depuis l'URL OpenWeatherMap en arrière-plan.
     * En cas d'erreur, affiche un placeholder textuel.
     */
    private void loadWeatherIcon(String iconUrl) {
        new Thread(() -> {
            try {
                URL url = new URL(iconUrl);
                Image img = ImageIO.read(url);
                Image scaled = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                SwingUtilities.invokeLater(() -> {
                    weatherIconLabel.setIcon(new ImageIcon(scaled));
                    weatherIconLabel.setText("");
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    weatherIconLabel.setIcon(null);
                    weatherIconLabel.setText(Icons.WEATHER_SUN);
                    weatherIconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
                });
            }
        }).start();
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public JTextField getSearchField() { return searchField; }
    public JButton getSearchButton() { return searchButton; }
}
