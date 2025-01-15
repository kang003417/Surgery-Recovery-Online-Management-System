package controller;

import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    // Method to authenticate user using email and password
    public boolean authenticateUser(String email, String enteredPassword) {
        // SQL query to check if the email and password match an existing user
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set email and password in the prepared statement
            stmt.setString(1, email);
            stmt.setString(2, enteredPassword);

            // Execute the query to check for a matching user
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            // Log the exception
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false; // Return false if there is an error during login
        }
    }
}
