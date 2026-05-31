package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        if (initialBalance > 0) {
            addTransaction(new Transaction("INITIAL DEPOSIT", initialBalance, initialBalance));
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addTransaction(new Transaction("DEPOSIT", amount, this.balance));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            addTransaction(new Transaction("WITHDRAWAL", amount, this.balance));
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
