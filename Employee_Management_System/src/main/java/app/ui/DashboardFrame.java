package app.ui;

import app.model.Employee;
import app.service.AuthService;
import app.service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {
    private AuthService authService;
    private EmployeeService employeeService;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private boolean isDarkMode = false;

    public DashboardFrame(AuthService authService) {
        this.authService = authService;
        this.employeeService = new EmployeeService();
        setTitle("EMS Dashboard - Logged in as: " + authService.getLoggedInUser().getUsername());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadEmployees();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefresh = new JButton("Refresh");
        JButton btnExport = new JButton("Export CSV");
        JButton btnToggleTheme = new JButton("Toggle Dark Mode");
        JButton btnLogout = new JButton("Logout");

        btnRefresh.addActionListener(e -> loadEmployees());
        btnExport.addActionListener(e -> exportData());
        btnToggleTheme.addActionListener(e -> toggleTheme());
        btnLogout.addActionListener(e -> {
            authService.logout();
            this.dispose();
            new LoginFrame(authService).setVisible(true);
        });

        topPanel.add(btnRefresh);
        topPanel.add(btnExport);
        topPanel.add(btnToggleTheme);
        topPanel.add(btnLogout);

        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Email", "Department", "Salary", "Hire Date"}, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);
        List<Employee> emps = employeeService.getAllEmployees();
        for (Employee e : emps) {
            tableModel.addRow(new Object[]{
                e.getId(), e.getFirstName(), e.getLastName(), e.getEmail(),
                e.getDepartmentName(), e.getSalary(), e.getHireDate()
            });
        }
    }

    private void exportData() {
        try {
            employeeService.exportToCSV("employees_export.csv");
            JOptionPane.showMessageDialog(this, "Exported successfully to employees_export.csv");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        Color bg = isDarkMode ? Color.DARK_GRAY : Color.WHITE;
        Color fg = isDarkMode ? Color.WHITE : Color.BLACK;
        
        getContentPane().setBackground(bg);
        employeeTable.setBackground(bg);
        employeeTable.setForeground(fg);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
