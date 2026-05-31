package service;

import model.Account;
import storage.FileManager;

import java.util.Map;
import java.util.Random;

public class BankingService {
    private Map<String, Account> accounts;
    private FileManager fileManager;

    public BankingService() {
        this.fileManager = new FileManager();
        this.accounts = fileManager.loadAccounts();
    }

    public Account createAccount(String name, double initialDeposit) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, name, initialDeposit);
        accounts.put(accountNumber, account);
        fileManager.saveAccounts(accounts);
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            fileManager.saveAccounts(accounts);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            boolean success = account.withdraw(amount);
            if (success) {
                fileManager.saveAccounts(accounts);
            }
            return success;
        }
        return false;
    }

    public boolean transfer(String fromAccountNum, String toAccountNum, double amount) {
        Account fromAccount = getAccount(fromAccountNum);
        Account toAccount = getAccount(toAccountNum);
        
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount && amount > 0) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            fileManager.saveAccounts(accounts);
            return true;
        }
        return false;
    }

    public boolean deleteAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            fileManager.saveAccounts(accounts);
            return true;
        }
        return false;
    }

    private String generateAccountNumber() {
        Random rand = new Random();
        String accNum;
        do {
            accNum = String.format("%06d", rand.nextInt(1000000));
        } while (accounts.containsKey(accNum));
        return accNum;
    }
}
