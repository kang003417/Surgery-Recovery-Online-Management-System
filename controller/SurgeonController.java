package controller;

import model.Surgeon;
import util.DatabaseUtil;
import java.sql.*;
import java.util.*;

public class SurgeonController {

    public Surgeon getSurgeonProfile(int surgeonId) {
    String query = "SELECT username, email, phone, department FROM Surgeon WHERE surgeonID = ?";

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        
        stmt.setInt(1, surgeonId);
        System.out.println("🔄 Running SQL Query: " + stmt.toString());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String username = rs.getString("username");
             String email = rs.getString("email");
            String phone = rs.getString("phone");
            String department = rs.getString("department");

            System.out.println("✅ Database returned:");
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
            System.out.println("Department: " + department);

            return new Surgeon(surgeonId, username, email, phone, department);
        } else {
            System.err.println("❌ No surgeon found with ID: " + surgeonId);
            
            // ✅ Print all available surgeon IDs
            Statement statement = connection.createStatement();
            ResultSet allSurgeons = statement.executeQuery("SELECT surgeonID, username FROM Surgeon");
            
            System.out.println("Available Surgeons:");
            while (allSurgeons.next()) {
                System.out.println("Surgeon ID: " + allSurgeons.getInt("surgeonID") +
                                   ", Username: " + allSurgeons.getString("username"));
            }
        }
    } catch (SQLException e) {
        System.err.println("❌ SQL Exception: " + e.getMessage());
    }
    return null;
}

    
    public boolean updateSurgeonProfile(int surgeonId, String username, String phone, String department) {
    String query = "UPDATE Surgeon SET username = ?, phone = ?, department = ? WHERE surgeonID = ?";

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, username);
        stmt.setString(2, phone);
        stmt.setString(3, department);
        stmt.setInt(4, surgeonId);

        int rowsAffected = stmt.executeUpdate();
        
        // ✅ Debug messages
        System.out.println("🔄 Executing update with:");
        System.out.println("Surgeon ID: " + surgeonId);
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);
        System.out.println("Department: " + department);
        System.out.println("Rows Affected: " + rowsAffected);

        return rowsAffected > 0;
    } catch (SQLException e) {
        System.err.println("❌ SQL exception occurred: " + e.getMessage());
        return false;
        }
    }
    
    public static List<String> getAllSurgeons() {
        List<String> surgeons = new ArrayList<>();
        String query = "SELECT userID, username FROM User WHERE userType = 'Surgeon'";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                surgeons.add(userID + ": " + username); // Format -> "29: Dr. Smith"
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return surgeons;
    }
}
