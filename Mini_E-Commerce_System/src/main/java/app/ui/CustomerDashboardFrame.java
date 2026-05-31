package app.ui;

import app.model.Product;
import app.model.CartItem;
import app.service.AuthService;
import app.service.CartService;
import app.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerDashboardFrame extends JFrame {
    private AuthService authService;
    private ProductService productService;
    private CartService cartService;
    
    private JTable productTable;
    private DefaultTableModel productModel;
    private JTable cartTable;
    private DefaultTableModel cartModel;
    private int cartId;
    private boolean isDarkMode = false;

    public CustomerDashboardFrame(AuthService authService) {
        this.authService = authService;
        this.productService = new ProductService();
        this.cartService = new CartService();
        
        this.cartId = cartService.getCartId(authService.getLoggedInUser().getId());
        
        setTitle("E-Commerce Dashboard - Welcome " + authService.getLoggedInUser().getUsername());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadProducts();
        loadCart();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        JButton btnToggleTheme = new JButton("Toggle Dark Mode");
        JButton btnLogout = new JButton("Logout");
        
        btnToggleTheme.addActionListener(e -> toggleTheme());
        btnLogout.addActionListener(e -> {
            authService.logout();
            dispose();
            new LoginFrame(authService).setVisible(true);
        });
        
        topPanel.add(btnToggleTheme);
        topPanel.add(btnLogout);
        
        productModel = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Stock"}, 0);
        productTable = new JTable(productModel);
        
        cartModel = new DefaultTableModel(new String[]{"Product ID", "Name", "Quantity", "Price"}, 0);
        cartTable = new JTable(cartModel);
        
        JPanel midPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        // Products View
        JPanel prodPanel = new JPanel(new BorderLayout());
        prodPanel.add(new JLabel("Products Catalog", SwingConstants.CENTER), BorderLayout.NORTH);
        prodPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);
        JButton btnAddCart = new JButton("Add Selected to Cart");
        btnAddCart.addActionListener(e -> addToCart());
        prodPanel.add(btnAddCart, BorderLayout.SOUTH);
        
        // Cart View
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(new JLabel("Your Shopping Cart", SwingConstants.CENTER), BorderLayout.NORTH);
        cartPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.addActionListener(e -> checkout());
        cartPanel.add(btnCheckout, BorderLayout.SOUTH);
        
        midPanel.add(prodPanel);
        midPanel.add(cartPanel);
        
        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
    }
    
    private void loadProducts() {
        productModel.setRowCount(0);
        List<Product> list = productService.getAllProducts();
        for (Product p : list) {
            productModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getStock()});
        }
    }
    
    private void loadCart() {
        cartModel.setRowCount(0);
        List<CartItem> items = cartService.getCartItems(cartId);
        for (CartItem ci : items) {
            cartModel.addRow(new Object[]{ci.getProductId(), ci.getProductName(), ci.getQuantity(), ci.getUnitPrice()});
        }
    }
    
    private void addToCart() {
        int row = productTable.getSelectedRow();
        if (row >= 0) {
            int prodId = (int) productModel.getValueAt(row, 0);
            cartService.addProductToCart(cartId, prodId, 1);
            loadCart();
            JOptionPane.showMessageDialog(this, "Added to cart!");
        }
    }
    
    private void checkout() {
        if (cartTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }
        boolean success = cartService.checkout(authService.getLoggedInUser().getId(), cartId, "Credit Card");
        if (success) {
            JOptionPane.showMessageDialog(this, "Order placed successfully!");
            loadCart();
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Checkout failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        Color bg = isDarkMode ? Color.DARK_GRAY : Color.WHITE;
        Color fg = isDarkMode ? Color.WHITE : Color.BLACK;
        
        getContentPane().setBackground(bg);
        productTable.setBackground(bg);
        productTable.setForeground(fg);
        cartTable.setBackground(bg);
        cartTable.setForeground(fg);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
