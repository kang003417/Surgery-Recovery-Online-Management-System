package controller;
import model.Payments;
import model.User;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import view.TrackPaymentPanel;

public class AdminController {
    
    private void syncPayments() {
        String insertQuery = "INSERT INTO payments (paymentID, patientID, patientName, patientEmail, paymentStatus, dateOfPayment, treatment, amountPaid) " +
                             "SELECT p.paymentID, u.userID AS patientID, u.username AS patientName, u.email AS patientEmail, " +
                             "'Completed' AS paymentStatus, p.paymentDate AS dateOfPayment, 'General Treatment' AS treatment, p.amount " +
                             "FROM payment p " +
                             "JOIN User u ON p.patientID = u.userID " +
                             "WHERE NOT EXISTS (SELECT 1 FROM payments WHERE payments.paymentID = p.paymentID)"; 

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ New payments synced successfully!");
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Exception in syncPayments: " + e.getMessage());
        }
    }
    
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
    
    public List<Payments> getAllPaymentsForTracking() {
        syncPayments(); // 🔄 Sync new payments before retrieving data
        
        List<Payments> paymentsList = new ArrayList<>();
        String query = "SELECT paymentID, patientID, patientName, patientEmail, paymentStatus, dateOfPayment, treatment, amountPaid FROM payments ORDER BY dateOfPayment DESC";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                paymentsList.add(new Payments(
                        rs.getInt("paymentID"),
                        rs.getInt("patientID"),
                        rs.getString("patientName"),
                        rs.getString("patientEmail"),
                        rs.getString("paymentStatus"),
                        rs.getDate("dateOfPayment"),
                        rs.getString("treatment"),
                        rs.getBigDecimal("amountPaid")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Exception in getAllPaymentsForTracking: " + e.getMessage());
        }
        return paymentsList;
    }
}
