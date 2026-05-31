package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transactionId;
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private double amount;
    private double resultingBalance;
    private LocalDateTime timestamp;

    public Transaction(String type, double amount, double resultingBalance) {
        this.transactionId = "TXN" + System.currentTimeMillis();
        this.type = type;
        this.amount = amount;
        this.resultingBalance = resultingBalance;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public double getResultingBalance() { return resultingBalance; }
    
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
    
    @Override
    public String toString() {
        return String.format("%-15s | %-12s | %10.2f | %12.2f", getFormattedTimestamp(), type, amount, resultingBalance);
    }
}
