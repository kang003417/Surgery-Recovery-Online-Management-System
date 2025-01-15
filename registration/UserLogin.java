package registration;

import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {

    // Method for login
    public boolean loginUser(String enteredEmail, String enteredPassword) {
        try {
            // Retrieve stored password from the database
            String storedPassword = getStoredPasswordFromDatabase(enteredEmail);

            if (storedPassword == null) {
                System.out.println("User not found.");
                return false;
            }

            // Compare the entered password with the stored password
            if (enteredPassword.equals(storedPassword)) {
                // Login successful
                System.out.println("Login Successful!");
                return true;
            } else {
                // Incorrect password
                System.out.println("Incorrect password.");
                return false;
            }
        } catch (SQLException e) {
            // Log the exception
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;  // Return false if there is an error during login
        }
    }

    // Method to get stored password from the database
    private String getStoredPasswordFromDatabase(String email) throws SQLException {
        String query = "SELECT password FROM User WHERE email = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            } else {
                return null;
            }
        }
    }
}
