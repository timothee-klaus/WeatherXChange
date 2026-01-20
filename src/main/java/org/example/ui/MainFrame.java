package org.example.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.example.views.WeatherPanel;
import org.example.views.CurrencyPanel;
import org.example.services.WeatherService;
import org.example.services.CurrencyService;

import java.awt.*;

/**
 * Fenêtre principale de l'application WeatherXChange.
 * Design moderne avec météo en haut et conversion de devises en bas.
 */
public class MainFrame extends JFrame {
    // Couleurs du thème
    public static final Color PRIMARY_COLOR = new Color(41, 128, 185);      // Bleu
    public static final Color SECONDARY_COLOR = new Color(39, 174, 96);     // Vert
    public static final Color BACKGROUND_COLOR = new Color(236, 240, 241);  // Gris clair
    public static final Color CARD_COLOR = Color.WHITE;
    public static final Color TEXT_COLOR = new Color(44, 62, 80);           // Gris foncé
    public static final Color TEXT_LIGHT = new Color(127, 140, 141);        // Gris moyen

    // Polices
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font VALUE_FONT = new Font("Segoe UI", Font.BOLD, 16);

    private WeatherPanel weatherPanel;
    private CurrencyPanel currencyPanel;
    private WeatherService weatherService;
    private CurrencyService currencyService;

    /**
     * Constructeur - initialise l'application
     */
    public MainFrame() {
        initializeServices();
        initializeUI();
        loadInitialData();
    }

    /**
     * Initialise les services
     */
    private void initializeServices() {
        weatherService = new WeatherService();
        currencyService = new CurrencyService();
    }

    /**
     * Initialise l'interface utilisateur
     */
    private void initializeUI() {
        setTitle("WeatherXChange");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 1080);
        setMinimumSize(new Dimension(800, 700));
        setLocationRelativeTo(null);

        // Look and feel moderne
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Layout principal
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(BACKGROUND_COLOR);

        // Header
        container.add(createHeader(), BorderLayout.NORTH);

        // Contenu principal (météo + devises)
        container.add(createMainContent(), BorderLayout.CENTER);

        // Footer
        container.add(createFooter(), BorderLayout.SOUTH);

        // Configurer les listeners
        setupListeners();

        setVisible(true);

        // Maximiser la fenêtre APRÈS setVisible pour que ça fonctionne correctement
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Crée le header de l'application
     */
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY_COLOR);
        header.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Titre
        JLabel titleLabel = new JLabel("☁ WeatherXChange");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);

        // Sous-titre
        JLabel subtitleLabel = new JLabel("Météo & Conversion de devises");
        subtitleLabel.setFont(LABEL_FONT);
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        header.add(titlePanel, BorderLayout.WEST);

        return header;
    }

    /**
     * Crée le contenu principal avec météo en haut et devises en bas
     */
    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel Météo en haut
        weatherPanel = new WeatherPanel();
        mainPanel.add(weatherPanel);

        // Panel Devises en bas
        currencyPanel = new CurrencyPanel();
        mainPanel.add(currencyPanel);

        return mainPanel;
    }

    /**
     * Crée le footer
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(new Color(52, 73, 94));
        footer.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel footerLabel = new JLabel("© 2026 WeatherXChange - Données fournies par OpenWeatherMap & ExchangeRate API");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(189, 195, 199));

        footer.add(footerLabel);

        return footer;
    }

    /**
     * Configure les listeners
     */
    private void setupListeners() {
        // Listener pour la conversion (avec ActionListener qui permet plusieurs clics)
        currencyPanel.setConvertListener(e -> {
            double result = currencyPanel.convert();
            currencyPanel.displayResult(result);
        });

        // Listener pour la recherche météo
        weatherPanel.getSearchButton().addActionListener(e -> searchWeather());
        weatherPanel.getSearchField().addActionListener(e -> searchWeather());
    }

    /**
     * Recherche la météo pour une ville
     */
    private void searchWeather() {
        // Récupère la saisie de l'utilisateur dans le champ de recherche
        String city = weatherPanel.getSearchField().getText().trim();

        // Vérifie que la saisie n'est pas vide
        if (!city.isEmpty()) {

            // Crée un nouveau Thread pour éviter de geler l'interface
            new Thread(() -> {
                try {
                    // Envoie la requête à l'API
                    weatherPanel.updateWeather(weatherService.getWeatherByCity(city));
                } catch (Exception ex) {
                    // Affiche une fenêtre avec l'erreur en cas d'échec
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this,
                                "Erreur: " + ex.getMessage(),
                                "Erreur de recherche",
                                JOptionPane.ERROR_MESSAGE);
                    });
                }
            }).start(); // Lance le Thread
        }
    }

    /**
     * Charge les données initiales
     */
    private void loadInitialData() {
        new Thread(() -> {
            try {
                // Charger météo Paris par défaut
                weatherPanel.updateWeather(weatherService.getWeatherByCity("Paris"));

                // Charger les taux de change
                currencyPanel.updateCurrencies(currencyService.getExchangeRates("EUR"));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this,
                            "Erreur de chargement: " + e.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    /**
     * Crée un bouton stylisé
     */
    public static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(LABEL_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Effet hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    /**
     * Crée un champ de texte stylisé
     */
    public static JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(VALUE_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        return field;
    }

    /**
     * Crée un label stylisé
     */
    public static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT_COLOR);
        return label;
    }

    /**
     * Crée une carte (panel) avec ombre et coins arrondis
     */
    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        return card;
    }
}

