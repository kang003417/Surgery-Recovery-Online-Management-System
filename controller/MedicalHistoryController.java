package controller;

import util.DatabaseUtil;
import model.MedicalHistory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryController {

    public List<String> getAllPatients() {
        List<String> patients = new ArrayList<>();
        String query = "SELECT userID, username FROM Patient";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                patients.add(rs.getInt("userID") + ": " + rs.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Error in getAllPatients: " + e.getMessage());
        }
        return patients;
    }

    public List<String> getAppointmentsByPatient(String patientUserId) {
    List<String> appointments = new ArrayList<>();
    String query = "SELECT appointmentID, appointmentDate, appointmentTime FROM Appointment WHERE patientID = ?";

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, Integer.parseInt(patientUserId)); // ✅ Use patientID directly
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // ✅ Format appointment details
            String appointmentDetails = rs.getInt("appointmentID") + " - " + rs.getString("appointmentDate") + " " + rs.getString("appointmentTime");
            appointments.add(appointmentDetails);
        }

    } catch (SQLException e) {
        System.err.println("❌ SQL Error in getAppointmentsByPatient: " + e.getMessage());
    }

    return appointments;
    }


    public boolean addMedicalHistory(int patientID, int surgeonID, int appointmentID, String conditions, String diagnosisDate, String treatment, String treatmentDate) {
    String query = "INSERT INTO MedicalHistory (patientID, surgeonID, appointmentID, conditions, diagnosisDate, treatment, treatmentDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {

        stmt.setInt(1, patientID);
        stmt.setInt(2, surgeonID);
        stmt.setInt(3, appointmentID); // ✅ Ensure it's an integer
        stmt.setString(4, conditions);
        stmt.setDate(5, Date.valueOf(diagnosisDate));
        stmt.setString(6, treatment);
        stmt.setDate(7, Date.valueOf(treatmentDate));

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;

    } catch (SQLException e) {
        System.err.println("❌ SQL Error in addMedicalHistory: " + e.getMessage());
        return false;
    }
    }
}
