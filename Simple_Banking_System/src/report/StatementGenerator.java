package report;

import model.Account;
import model.Transaction;

public class StatementGenerator {
    public void printMiniStatement(Account account) {
        System.out.println("\n===========================================================");
        System.out.println("                      MINI STATEMENT                       ");
        System.out.println("===========================================================");
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Account Holder : " + account.getAccountHolderName());
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("-----------------------------------------------------------");
        System.out.println(String.format("%-19s | %-12s | %10s | %12s", "Date/Time", "Type", "Amount", "Balance"));
        System.out.println("-----------------------------------------------------------");
        
        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : account.getTransactions()) {
                System.out.println(t.toString());
            }
        }
        System.out.println("===========================================================\n");
    }
}
