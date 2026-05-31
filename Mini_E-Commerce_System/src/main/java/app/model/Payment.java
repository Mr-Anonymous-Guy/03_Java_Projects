package app.model;

import java.sql.Timestamp;

public class Payment {
    private int id;
    private int orderId;
    private String paymentMethod;
    private double amount;
    private Timestamp paymentDate;
    private String status;

    public Payment(int id, int orderId, String paymentMethod, double amount, Timestamp paymentDate, String status) {
        this.id = id;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }
}
