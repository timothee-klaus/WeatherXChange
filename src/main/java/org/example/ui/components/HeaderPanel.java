package org.example.ui.components;

import org.example.ui.Icons;
import org.example.ui.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Composant Header de l'application.
 * Affiche le titre et le sous-titre avec le style de l'application.
 */
public class HeaderPanel extends JPanel {

    public HeaderPanel(String title, String subtitle) {
        setLayout(new BorderLayout());
        setBackground(Theme.PRIMARY);
        setBorder(new EmptyBorder(20, 30, 20, 30));

        add(createTitlePanel(title, subtitle), BorderLayout.WEST);
    }

    private JPanel createTitlePanel(String title, String subtitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(Theme.TITLE);
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(Theme.BODY);
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitleLabel);

        return panel;
    }
}

