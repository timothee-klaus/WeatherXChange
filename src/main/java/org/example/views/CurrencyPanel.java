package org.example.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.example.models.CurrencyData;
import org.example.ui.MainFrame;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel de conversion de devises avec un design moderne.
 */
public class CurrencyPanel extends JPanel {
    private JComboBox<String> baseCurrencyCombo, targetCurrencyCombo;
    private JTextField amountField, resultField;
    private JButton convertButton;
    private CurrencyData currentData;
    private ActionListener currentListener;

    public CurrencyPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(MainFrame.BACKGROUND_COLOR);
        setBorder(new EmptyBorder(25, 30, 25, 30));

        // Titre
        JLabel titleLabel = new JLabel("💱 Conversion de devises");
        titleLabel.setFont(MainFrame.HEADER_FONT);
        titleLabel.setForeground(MainFrame.TEXT_COLOR);
        add(titleLabel, BorderLayout.NORTH);

        // Card de conversion
        add(createConversionCard(), BorderLayout.CENTER);
    }

    /**
     * Crée la carte de conversion
     */
    private JPanel createConversionCard() {
        JPanel card = MainFrame.createCard();
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Devise de base
        gbc.gridx = 0; gbc.gridy = 0;
        card.add(MainFrame.createStyledLabel("💵 Devise de base:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        baseCurrencyCombo = createStyledComboBox();
        card.add(baseCurrencyCombo, gbc);

        // Montant
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        card.add(MainFrame.createStyledLabel("💰 Montant:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        amountField = MainFrame.createStyledTextField();
        card.add(amountField, gbc);

        // Devise cible
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        card.add(MainFrame.createStyledLabel("🎯 Devise cible:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        targetCurrencyCombo = createStyledComboBox();
        card.add(targetCurrencyCombo, gbc);

        // Bouton convertir
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        convertButton = MainFrame.createStyledButton("🔄 Convertir", MainFrame.SECONDARY_COLOR);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(convertButton);
        card.add(buttonPanel, gbc);

        // Résultat
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel resultPanel = createResultPanel();
        card.add(resultPanel, gbc);

        return card;
    }

    /**
     * Crée le panel de résultat
     */
    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(232, 245, 233));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MainFrame.SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        JLabel resultLabel = new JLabel("✅ Résultat:");
        resultLabel.setFont(MainFrame.LABEL_FONT);
        resultLabel.setForeground(MainFrame.SECONDARY_COLOR);

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(new Font("Segoe UI", Font.BOLD, 24));
        resultField.setForeground(MainFrame.SECONDARY_COLOR);
        resultField.setBackground(new Color(232, 245, 233));
        resultField.setBorder(null);
        resultField.setHorizontalAlignment(JTextField.CENTER);
        resultField.setText("0.00");

        panel.add(resultLabel, BorderLayout.WEST);
        panel.add(resultField, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Crée un ComboBox stylisé
     */
    private JComboBox<String> createStyledComboBox() {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(MainFrame.VALUE_FONT);
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        return combo;
    }

    /**
     * Met à jour les devises disponibles
     */
    public void updateCurrencies(CurrencyData data) {
        SwingUtilities.invokeLater(() -> {
            currentData = data;
            baseCurrencyCombo.removeAllItems();
            targetCurrencyCombo.removeAllItems();

            // Trier les devises par ordre alphabétique
            java.util.List<String> currencies = new java.util.ArrayList<>(data.getRates().keySet());
            java.util.Collections.sort(currencies);

            for (String currency : currencies) {
                baseCurrencyCombo.addItem(currency);
                targetCurrencyCombo.addItem(currency);
            }

            // Sélectionner EUR et USD par défaut
            baseCurrencyCombo.setSelectedItem("EUR");
            targetCurrencyCombo.setSelectedItem("USD");
        });
    }

    /**
     * Définit le listener pour le bouton de conversion.
     * Supprime l'ancien listener avant d'ajouter le nouveau pour éviter les doublons.
     */
    public void setConvertListener(ActionListener listener) {
        // Supprimer l'ancien listener s'il existe
        if (currentListener != null) {
            convertButton.removeActionListener(currentListener);
            amountField.removeActionListener(currentListener);
        }

        // Ajouter le nouveau listener
        currentListener = listener;
        convertButton.addActionListener(currentListener);
        amountField.addActionListener(currentListener);
    }

    /**
     * Effectue la conversion
     */
    public double convert() {
        try {
            if (currentData == null) {
                return 0;
            }

            double amount = Double.parseDouble(amountField.getText().replace(",", "."));
            String base = (String) baseCurrencyCombo.getSelectedItem();
            String target = (String) targetCurrencyCombo.getSelectedItem();

            if (base == null || target == null) {
                return 0;
            }

            Double baseRate = currentData.getRate(base);
            Double targetRate = currentData.getRate(target);

            if (baseRate == null || targetRate == null) {
                return 0;
            }

            // Conversion: montant / taux_base * taux_cible
            return (amount / baseRate) * targetRate;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Affiche le résultat de la conversion
     */
    public void displayResult(double result) {
        String target = (String) targetCurrencyCombo.getSelectedItem();
        if (target != null) {
            resultField.setText(String.format("%.2f %s", result, target));
        } else {
            resultField.setText("0.00");
        }
    }
}

