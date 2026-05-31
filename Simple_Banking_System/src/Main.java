import model.Account;
import service.BankingService;
import utils.InputValidator;
import report.StatementGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankingService bankingService = new BankingService();
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator(scanner);
        StatementGenerator reportGenerator = new StatementGenerator();

        System.out.println("=========================================");
        System.out.println("    Welcome to Simple Banking System     ");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Balance Inquiry");
            System.out.println("6. Transaction History (Mini Statement)");
            System.out.println("7. Delete Account");
            System.out.println("8. Exit");
            
            int choice = validator.getValidInt("Enter your choice (1-8): ");
            
            switch (choice) {
                case 1:
                    String name = validator.getValidString("Enter Account Holder Name: ");
                    double initialDeposit = validator.getValidDouble("Enter Initial Deposit Amount: $");
                    Account newAcc = bankingService.createAccount(name, initialDeposit);
                    System.out.println("Account created successfully! Your Account Number is: " + newAcc.getAccountNumber());
                    break;
                case 2:
                    String depAccNum = validator.getValidString("Enter Account Number: ");
                    double depAmt = validator.getValidDouble("Enter Deposit Amount: $");
                    if (bankingService.deposit(depAccNum, depAmt)) {
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Deposit failed. Check account number.");
                    }
                    break;
                case 3:
                    String withAccNum = validator.getValidString("Enter Account Number: ");
                    double withAmt = validator.getValidDouble("Enter Withdrawal Amount: $");
                    if (bankingService.withdraw(withAccNum, withAmt)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Withdrawal failed. Insufficient funds or invalid account.");
                    }
                    break;
                case 4:
                    String fromAccNum = validator.getValidString("Enter From Account Number: ");
                    String toAccNum = validator.getValidString("Enter To Account Number: ");
                    double transAmt = validator.getValidDouble("Enter Transfer Amount: $");
                    if (bankingService.transfer(fromAccNum, toAccNum, transAmt)) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Transfer failed. Check account numbers and balance.");
                    }
                    break;
                case 5:
                    String balAccNum = validator.getValidString("Enter Account Number: ");
                    Account balAcc = bankingService.getAccount(balAccNum);
                    if (balAcc != null) {
                        System.out.println("Current Balance for Account " + balAccNum + ": $" + String.format("%.2f", balAcc.getBalance()));
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    String stmtAccNum = validator.getValidString("Enter Account Number: ");
                    Account stmtAcc = bankingService.getAccount(stmtAccNum);
                    if (stmtAcc != null) {
                        reportGenerator.printMiniStatement(stmtAcc);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    String delAccNum = validator.getValidString("Enter Account Number to Delete: ");
                    if (bankingService.deleteAccount(delAccNum)) {
                        System.out.println("Account deleted successfully.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 8:
                    System.out.println("Thank you for using Simple Banking System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-8.");
            }
        }
    }
}
