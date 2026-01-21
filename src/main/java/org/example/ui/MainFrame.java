package org.example.ui;

import org.example.services.CurrencyService;
import org.example.services.WeatherService;
import org.example.ui.components.FooterPanel;
import org.example.ui.components.HeaderPanel;
import org.example.views.CurrencyPanel;
import org.example.views.WeatherPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Fenêtre principale de l'application WeatherXChange.
 * Organise les composants avec météo en haut et conversion de devises en bas.
 */
public class MainFrame extends JFrame {

    private static final String APP_TITLE = Icons.WEATHER_CLOUD + " WeatherXChange";
    private static final String APP_SUBTITLE = "Meteo et Conversion de devises";
    private static final String FOOTER_TEXT = "© 2026 WeatherXChange - Données fournies par OpenWeatherMap & ExchangeRate API";
    private static final String DEFAULT_CITY = "Lomé";
    private static final String DEFAULT_CURRENCY = "EUR";

    private WeatherPanel weatherPanel;
    private CurrencyPanel currencyPanel;
    private WeatherService weatherService;
    private CurrencyService currencyService;

    public MainFrame() {
        Theme.applySystemLookAndFeel();
        initializeServices();
        initializeUI();
        setupListeners();
        loadInitialData();
    }

    private void initializeServices() {
        weatherService = new WeatherService();
        currencyService = new CurrencyService();
    }

    private void initializeUI() {
        configureWindow();
        buildLayout();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void configureWindow() {
        setTitle("WeatherXChange");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 1080);
        setMinimumSize(new Dimension(800, 700));
        setLocationRelativeTo(null);
    }

    private void buildLayout() {
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.setBackground(Theme.BACKGROUND);

        content.add(new HeaderPanel(APP_TITLE, APP_SUBTITLE), BorderLayout.NORTH);
        content.add(createMainContent(), BorderLayout.CENTER);
        content.add(new FooterPanel(FOOTER_TEXT), BorderLayout.SOUTH);
    }

    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Theme.BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        weatherPanel = new WeatherPanel();
        currencyPanel = new CurrencyPanel();

        // Utiliser des wrappers pour un meilleur redimensionnement
        mainPanel.add(createPanelWrapper(weatherPanel));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createPanelWrapper(currencyPanel));

        return mainPanel;
    }

    private JPanel createPanelWrapper(JPanel panel) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Theme.BACKGROUND);
        wrapper.add(panel, BorderLayout.CENTER);
        return wrapper;
    }

    private void setupListeners() {
        currencyPanel.setConvertListener(e -> {
            double result = currencyPanel.convert();
            currencyPanel.displayResult(result);
        });

        weatherPanel.getSearchButton().addActionListener(e -> searchWeather());
        weatherPanel.getSearchField().addActionListener(e -> searchWeather());
    }

    private void searchWeather() {
        String city = weatherPanel.getSearchField().getText().trim();
        if (city.isEmpty()) return;

        new Thread(() -> {
            try {
                weatherPanel.updateWeather(weatherService.getWeatherByCity(city));
            } catch (Exception ex) {
                showError("Erreur de recherche", ex.getMessage());
            }
        }).start();
    }

    private void loadInitialData() {
        new Thread(() -> {
            try {
                weatherPanel.updateWeather(weatherService.getWeatherByCity(DEFAULT_CITY));
                currencyPanel.updateCurrencies(currencyService.getExchangeRates(DEFAULT_CURRENCY));
            } catch (Exception e) {
                showError("Erreur de chargement", e.getMessage());
            }
        }).start();
    }

    private void showError(String title, String message) {
        SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(this, "Erreur: " + message, title, JOptionPane.ERROR_MESSAGE)
        );
    }
}

