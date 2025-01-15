package controller;

import model.Surgeon;
import util.DatabaseUtil;
import java.sql.*;

public class SurgeonController {

    public Surgeon getSurgeonProfile(int surgeonId) {
        Surgeon surgeon = null;
        String query = "SELECT * FROM Surgeon WHERE surgeonID = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, surgeonId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                surgeon = new Surgeon(
                    rs.getInt("surgeonID"),
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("department")
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return surgeon;
    }

    public boolean updateSurgeonProfile(int surgeonId, String username, String email, String phone, String department) {
        String query = "UPDATE Surgeon SET username = ?, email = ?, phone = ?, department = ? WHERE surgeonID = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, department);
            stmt.setInt(5, surgeonId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }
}
