package app.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private double totalAmount;
    private Timestamp orderDate;
    private String status;

    public Order(int id, int userId, double totalAmount, Timestamp orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public double getTotalAmount() { return totalAmount; }
    public Timestamp getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
}
