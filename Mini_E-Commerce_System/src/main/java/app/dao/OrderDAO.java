package app.dao;

import app.model.CartItem;
import app.model.Order;
import app.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class OrderDAO {

    public boolean placeOrder(int userId, double totalAmount, List<CartItem> items, String paymentMethod) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Transaction Start
            
            // 1. Create Order
            String orderQuery = "INSERT INTO orders (user_id, total_amount, status) VALUES (?, ?, 'COMPLETED')";
            PreparedStatement pstmtOrder = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtOrder.setInt(1, userId);
            pstmtOrder.setDouble(2, totalAmount);
            pstmtOrder.executeUpdate();
            
            ResultSet rsKeys = pstmtOrder.getGeneratedKeys();
            int orderId = -1;
            if (rsKeys.next()) {
                orderId = rsKeys.getInt(1);
            }
            
            // 2. Insert Order Items and Update Stock
            String itemQuery = "INSERT INTO order_items (order_id, product_id, quantity, price_at_time) VALUES (?, ?, ?, ?)";
            String stockQuery = "UPDATE products SET stock = stock - ? WHERE id = ?";
            
            PreparedStatement pstmtItem = conn.prepareStatement(itemQuery);
            PreparedStatement pstmtStock = conn.prepareStatement(stockQuery);
            
            for (CartItem item : items) {
                pstmtItem.setInt(1, orderId);
                pstmtItem.setInt(2, item.getProductId());
                pstmtItem.setInt(3, item.getQuantity());
                pstmtItem.setDouble(4, item.getUnitPrice());
                pstmtItem.addBatch();
                
                pstmtStock.setInt(1, item.getQuantity());
                pstmtStock.setInt(2, item.getProductId());
                pstmtStock.addBatch();
            }
            
            pstmtItem.executeBatch();
            pstmtStock.executeBatch();
            
            // 3. Record Payment
            String paymentQuery = "INSERT INTO payments (order_id, payment_method, amount, status) VALUES (?, ?, ?, 'COMPLETED')";
            PreparedStatement pstmtPayment = conn.prepareStatement(paymentQuery);
            pstmtPayment.setInt(1, orderId);
            pstmtPayment.setString(2, paymentMethod);
            pstmtPayment.setDouble(3, totalAmount);
            pstmtPayment.executeUpdate();
            
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (Exception ex) { ex.printStackTrace(); }
            }
        }
    }
}
