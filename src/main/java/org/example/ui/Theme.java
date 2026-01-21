package org.example.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Classe utilitaire définissant le thème visuel de l'application.
 * Centralise les couleurs, polices et méthodes de création de composants stylisés.
 */
public final class Theme {
    
    // ===== COULEURS =====
    public static final Color PRIMARY = new Color(41, 128, 185);
    public static final Color PRIMARY_DARK = new Color(31, 97, 141);
    public static final Color SECONDARY = new Color(39, 174, 96);
    public static final Color SECONDARY_DARK = new Color(30, 132, 73);
    public static final Color BACKGROUND = new Color(236, 240, 241);
    public static final Color CARD = Color.WHITE;
    public static final Color TEXT_DARK = new Color(44, 62, 80);
    public static final Color TEXT_LIGHT = new Color(127, 140, 141);
    public static final Color FOOTER = new Color(52, 73, 94);
    public static final Color BORDER = new Color(189, 195, 199);
    public static final Color SUCCESS_BG = new Color(232, 245, 233);
    
    // ===== POLICES =====
    private static final String FONT_FAMILY = "Segoe UI";
    public static final Font TITLE = new Font(FONT_FAMILY, Font.BOLD, 28);
    public static final Font HEADER = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font BODY = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font BODY_BOLD = new Font(FONT_FAMILY, Font.BOLD, 16);
    public static final Font SMALL = new Font(FONT_FAMILY, Font.PLAIN, 12);
    public static final Font RESULT = new Font(FONT_FAMILY, Font.BOLD, 24);
    
    // ===== DIMENSIONS =====
    public static final int PADDING_SMALL = 10;
    public static final int PADDING_MEDIUM = 15;
    public static final int PADDING_LARGE = 25;
    
    private Theme() {}
    
    /**
     * Crée un bouton avec le style de l'application.
     */
    public static JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(BODY);
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        Color hoverColor = backgroundColor.darker();
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }
    
    /**
     * Crée un champ de texte avec le style de l'application.
     */
    public static JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(BODY_BOLD);
        field.setForeground(TEXT_DARK);
        field.setBackground(Color.WHITE);
        field.setCaretColor(TEXT_DARK);
        field.setOpaque(true);
        field.setBorder(createInputBorder());

        // Forcer les couleurs dans UIDefaults pour s'assurer qu'elles s'appliquent
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", TEXT_DARK);
        UIManager.put("TextField.caretForeground", TEXT_DARK);

        return field;
    }
    
    /**
     * Crée un label avec le style de l'application.
     */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY);
        label.setForeground(TEXT_DARK);
        return label;
    }
    
    /**
     * Crée une carte (panel avec bordure et fond blanc).
     */
    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(CARD);
        card.setBorder(createCardBorder());
        return card;
    }
    
    /**
     * Crée une bordure pour les champs de saisie.
     */
    public static Border createInputBorder() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );
    }
    
    /**
     * Crée une bordure pour les cartes.
     */
    public static Border createCardBorder() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );
    }
    
    /**
     * Initialise le thème de l'application.
     * Utilise le Look and Feel Metal (cross-platform) pour un contrôle complet des couleurs.
     */
    public static void applySystemLookAndFeel() {
        try {
            // Utiliser Metal (cross-platform) pour éviter les problèmes de thème sombre
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            // Forcer les couleurs des composants
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextField.foreground", TEXT_DARK);
            UIManager.put("TextField.caretForeground", TEXT_DARK);
            UIManager.put("TextArea.background", Color.WHITE);
            UIManager.put("TextArea.foreground", TEXT_DARK);
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("ComboBox.foreground", TEXT_DARK);
            UIManager.put("ComboBox.selectionBackground", PRIMARY);
            UIManager.put("ComboBox.selectionForeground", Color.WHITE);
            UIManager.put("Panel.background", BACKGROUND);
            UIManager.put("Label.foreground", TEXT_DARK);
        } catch (Exception e) {
            System.err.println("Impossible d'appliquer le thème: " + e.getMessage());
        }
    }

    /**
     * Stylise un champ de texte existant avec les couleurs de l'application.
     */
    public static void styleTextField(JTextField field) {
        field.setForeground(TEXT_DARK);
        field.setBackground(Color.WHITE);
        field.setCaretColor(TEXT_DARK);
        field.setOpaque(true);
    }

    /**
     * Stylise une ComboBox existante avec les couleurs de l'application.
     */
    public static void styleComboBox(JComboBox<?> combo) {
        combo.setBackground(Color.WHITE);
        combo.setForeground(TEXT_DARK);
        combo.setOpaque(true);
    }
}

