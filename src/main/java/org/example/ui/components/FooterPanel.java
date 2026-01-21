package org.example.ui.components;

import org.example.ui.Theme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Composant Footer de l'application.
 * Affiche les informations de copyright et crédits.
 */
public class FooterPanel extends JPanel {
    
    public FooterPanel(String text) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Theme.FOOTER);
        setBorder(new EmptyBorder(10, 0, 10, 0));
        
        JLabel label = new JLabel(text);
        label.setFont(Theme.SMALL);
        label.setForeground(Theme.BORDER);
        
        add(label);
    }
}

