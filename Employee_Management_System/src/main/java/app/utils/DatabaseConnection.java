package app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ems_db";

    private static final String USER = "ems_user";
    private static final String PASSWORD = "ems123";

    public static Connection getConnection() throws SQLException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Trying to connect...");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("DATABASE CONNECTED SUCCESSFULLY!");

            return conn;

        } catch (ClassNotFoundException e) {

            System.out.println("Driver not found!");

            throw new SQLException(e);

        }
    }
}