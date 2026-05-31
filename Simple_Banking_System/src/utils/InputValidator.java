package utils;

import java.util.Scanner;

public class InputValidator {
    private Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getValidString(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) System.exit(0);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            if (!scanner.hasNextLine()) System.exit(0);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    public double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) System.exit(0);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= 0) return value;
                System.out.println("Value must be non-negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    public int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) System.exit(0);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
