package controller;

import model.Patient;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileController {

    public Patient getPatientProfile(int userId) {
        Patient patient = null;
        String query = "SELECT * FROM Patient WHERE userID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                patient = new Patient(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("birthDate"),
                    rs.getString("bloodGroup"),
                    rs.getString("emergencyContact"),
                    rs.getString("address"),
                    rs.getString("postcode")
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return patient;
    }

    public boolean updatePatientProfile(Patient patient) {
        String query = "UPDATE Patient SET username = ?, phone = ?, email = ?, birthDate = ?, bloodGroup = ?, emergencyContact = ?, address = ?, postcode = ? WHERE userID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getPhone());
            stmt.setString(3, patient.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(patient.getBirthday()));
            stmt.setString(5, patient.getBloodGroup());
            stmt.setString(6, patient.getEmergencyContact());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8, patient.getPostcode());
            stmt.setInt(9, patient.getUserId());
        
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }
}
