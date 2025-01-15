package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/surgeryrecoverysystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2003417cwk";

    // Private constructor to prevent instantiation
    private DatabaseUtil() {
    }

    // Get a connection instance
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    // Close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }
}
