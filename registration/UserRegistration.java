package registration;

import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistration {

    // Method to register a new user
    public boolean registerUser(String email, String password, String username, String postcode, String userType) {
        
        // SQL query to insert a new user into the database
        String query = "INSERT INTO User (email, password, username, postcode, userType) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set email, hashed password, username, userType, and salt in the prepared statement
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, username);
            stmt.setString(4, postcode);
            stmt.setString(5, userType);

            // Execute the query to insert the data into the database
            int rowsAffected = stmt.executeUpdate();

            // Return true if registration was successful (1 or more rows affected)
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Log the exception
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false; // Return false if there is an error during registration
        }
    }
}
