package org.example;

import org.example.ui.MainFrame;
import javax.swing.*;

/**
 * Point d'entrée de l'application WeatherXChange.
 * Lance simplement la fenêtre principale.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

