package app.ui;

import app.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private AuthService authService;

    public LoginFrame(AuthService authService) {
        this.authService = authService;
        setTitle("Employee Management System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(this::handleLogin);
        panel.add(new JLabel()); // spacer
        panel.add(btnLogin);

        add(panel);
    }

    private void handleLogin(ActionEvent e) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (authService.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose();
            new DashboardFrame(authService).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials or database not reachable.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
