package org.example.views;
import org.example.models.CurrencyData;
import org.example.ui.Icons;
import org.example.ui.Theme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CurrencyPanel extends JPanel {
    private JComboBox<String> baseCurrencyCombo;
    private JComboBox<String> targetCurrencyCombo;
    private JTextField amountField;
    private JLabel resultLabel;
    private JLabel rateInfoLabel;
    private JButton convertButton;
    private CurrencyData currentData;
    private ActionListener currentListener;
    public CurrencyPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(Theme.BACKGROUND);
        setBorder(new EmptyBorder(20, 25, 20, 25));
        add(createTitle(), BorderLayout.NORTH);
        add(createMainCard(), BorderLayout.CENTER);
    }
    private JLabel createTitle() {
        JLabel title = new JLabel(Icons.EXCHANGE + " Conversion de devises");
        title.setFont(Theme.HEADER);
        title.setForeground(Theme.TEXT_DARK);
        return title;
    }
    private JPanel createMainCard() {
        JPanel card = Theme.createCard();
        card.setLayout(new BorderLayout(0, 25));
        card.add(createConversionSection(), BorderLayout.CENTER);
        card.add(createResultSection(), BorderLayout.SOUTH);
        return card;
    }
    private JPanel createConversionSection() {
        JPanel section = new JPanel(new GridLayout(1, 3, 15, 0));
        section.setOpaque(false);
        section.add(createInputCard("De", baseCurrencyCombo = createCurrencyCombo()));
        section.add(createAmountCard());
        section.add(createInputCard("Vers", targetCurrencyCombo = createCurrencyCombo()));
        return section;
    }
    private JPanel createInputCard(String label, JComboBox<String> combo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        JLabel titleLabel = new JLabel(label);
        titleLabel.setFont(Theme.BODY);
        titleLabel.setForeground(Theme.TEXT_LIGHT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(combo);
        return card;
    }
    private JPanel createAmountCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        JLabel titleLabel = new JLabel("Montant");
        titleLabel.setFont(Theme.BODY);
        titleLabel.setForeground(Theme.TEXT_LIGHT);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        amountField = new JTextField();
        amountField.setFont(new Font("Segoe UI", Font.BOLD, 18));
        amountField.setForeground(Theme.TEXT_DARK);
        amountField.setBackground(Color.WHITE);
        amountField.setCaretColor(Theme.TEXT_DARK);
        amountField.setAlignmentX(Component.LEFT_ALIGNMENT);
        amountField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.BORDER, 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(amountField);
        return card;
    }
    private JPanel createResultSection() {
        JPanel section = new JPanel(new BorderLayout(0, 15));
        section.setOpaque(false);
        convertButton = Theme.createButton(Icons.REFRESH + " Convertir", Theme.SECONDARY);
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(convertButton);
        JPanel resultCard = new JPanel();
        resultCard.setLayout(new BoxLayout(resultCard, BoxLayout.Y_AXIS));
        resultCard.setBackground(Theme.SUCCESS_BG);
        resultCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.SECONDARY, 2),
            new EmptyBorder(20, 25, 20, 25)
        ));
        resultLabel = new JLabel("0.00");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        resultLabel.setForeground(Theme.SECONDARY);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rateInfoLabel = new JLabel("Entrez un montant pour convertir");
        rateInfoLabel.setFont(Theme.BODY);
        rateInfoLabel.setForeground(Theme.TEXT_LIGHT);
        rateInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultCard.add(resultLabel);
        resultCard.add(Box.createVerticalStrut(8));
        resultCard.add(rateInfoLabel);
        section.add(buttonWrapper, BorderLayout.NORTH);
        section.add(resultCard, BorderLayout.CENTER);
        return section;
    }
    private JComboBox<String> createCurrencyCombo() {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        combo.setBackground(Color.WHITE);
        combo.setForeground(Theme.TEXT_DARK);
        combo.setBorder(BorderFactory.createLineBorder(Theme.BORDER, 1));
        return combo;
    }
    public void updateCurrencies(CurrencyData data) {
        SwingUtilities.invokeLater(() -> {
            currentData = data;
            List<String> currencies = new ArrayList<>(data.getRates().keySet());
            Collections.sort(currencies);
            baseCurrencyCombo.removeAllItems();
            targetCurrencyCombo.removeAllItems();
            for (String currency : currencies) {
                baseCurrencyCombo.addItem(currency);
                targetCurrencyCombo.addItem(currency);
            }
            baseCurrencyCombo.setSelectedItem("EUR");
            targetCurrencyCombo.setSelectedItem("XOF");
        });
    }
    public void setConvertListener(ActionListener listener) {
        if (currentListener != null) {
            convertButton.removeActionListener(currentListener);
            amountField.removeActionListener(currentListener);
        }
        currentListener = listener;
        convertButton.addActionListener(currentListener);
        amountField.addActionListener(currentListener);
    }
    public double convert() {
        if (currentData == null) return 0;
        try {
            double amount = Double.parseDouble(amountField.getText().replace(",", "."));
            String base = (String) baseCurrencyCombo.getSelectedItem();
            String target = (String) targetCurrencyCombo.getSelectedItem();
            if (base == null || target == null) return 0;
            Double baseRate = currentData.getRate(base);
            Double targetRate = currentData.getRate(target);
            if (baseRate == null || targetRate == null) return 0;
            return (amount / baseRate) * targetRate;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public void displayResult(double result) {
        String base = (String) baseCurrencyCombo.getSelectedItem();
        String target = (String) targetCurrencyCombo.getSelectedItem();
        if (target != null && base != null) {
            resultLabel.setText(String.format("%.2f %s", result, target));
            try {
                double amount = Double.parseDouble(amountField.getText().replace(",", "."));
                double rate = result / amount;
                rateInfoLabel.setText(String.format("1 %s = %.4f %s", base, rate, target));
            } catch (NumberFormatException e) {
                rateInfoLabel.setText("Taux de conversion");
            }
        } else {
            resultLabel.setText("0.00");
            rateInfoLabel.setText("Entrez un montant pour convertir");
        }
    }
}
