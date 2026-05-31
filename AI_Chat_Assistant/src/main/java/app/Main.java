package app;

import app.ui.ChatDashboardFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ChatDashboardFrame dashboard = new ChatDashboardFrame();
            dashboard.setVisible(true);
        });
    }
}
