package app.ui;

import app.model.Book;
import app.service.AuthService;
import app.service.LibraryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {
    private AuthService authService;
    private LibraryService libraryService;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private boolean isDarkMode = false;

    public DashboardFrame(AuthService authService) {
        this.authService = authService;
        this.libraryService = new LibraryService();
        setTitle("LMS Dashboard - Role: " + authService.getLoggedInUser().getRole());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadBooks();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefresh = new JButton("Refresh");
        JButton btnExport = new JButton("Export Books CSV");
        JButton btnToggleTheme = new JButton("Toggle Dark Mode");
        JButton btnLogout = new JButton("Logout");

        btnRefresh.addActionListener(e -> loadBooks());
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

        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "ISBN", "Year", "Total", "Available"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        List<Book> books = libraryService.getAllBooks();
        for (Book b : books) {
            tableModel.addRow(new Object[]{
                b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(),
                b.getPublishedYear(), b.getTotalCopies(), b.getAvailableCopies()
            });
        }
    }

    private void exportData() {
        try {
            libraryService.exportBooksToCSV("books_export.csv");
            JOptionPane.showMessageDialog(this, "Exported successfully to books_export.csv");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        Color bg = isDarkMode ? Color.DARK_GRAY : Color.WHITE;
        Color fg = isDarkMode ? Color.WHITE : Color.BLACK;
        
        getContentPane().setBackground(bg);
        bookTable.setBackground(bg);
        bookTable.setForeground(fg);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
