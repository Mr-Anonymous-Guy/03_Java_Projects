package app.ui;

import app.service.AuthService;
import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {
    private AuthService authService;

    public AdminDashboardFrame(AuthService authService) {
        this.authService = authService;
        setTitle("Admin Dashboard - E-Commerce");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lblWelcome = new JLabel("Welcome Admin: " + authService.getLoggedInUser().getUsername(), SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            authService.logout();
            dispose();
            new LoginFrame(authService).setVisible(true);
        });
        
        panel.add(lblWelcome, BorderLayout.CENTER);
        panel.add(btnLogout, BorderLayout.SOUTH);
        add(panel);
    }
}
