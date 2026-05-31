package storage;

import model.Account;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private static final String DATA_FILE = "data/accounts.dat";

    public void saveAccounts(Map<String, Account> accounts) {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Account> loadAccounts() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data. Starting fresh. " + e.getMessage());
            return new HashMap<>();
        }
    }
}
