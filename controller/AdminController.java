package controller;

import model.User;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("userType"),
                    rs.getString("bloodGroup"),
                    rs.getDate("birthDate").toString(),
                    rs.getString("gender"),
                    rs.getString("postcode")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM User WHERE userID = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("userType"),
                    rs.getString("bloodGroup"),
                    rs.getDate("birthDate").toString(),
                    rs.getString("gender"),
                    rs.getString("postcode")
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return user;
    }

    public boolean updateUserProfile(int userId, String username, String bloodGroup, String phone, String birthDate, String email, String emergencyContact, String address, String postcode) {
        String query = "UPDATE User SET username = ?, bloodGroup = ?, phone = ?, birthDate = ?, email = ?, postcode = ? WHERE userID = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, bloodGroup);
            stmt.setString(3, phone);
            stmt.setDate(4, Date.valueOf(birthDate)); // Convert birthDate to SQL date
            stmt.setString(5, email);
            stmt.setInt(6, userId);
            stmt.setString(7, postcode);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM User WHERE userID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }
}
