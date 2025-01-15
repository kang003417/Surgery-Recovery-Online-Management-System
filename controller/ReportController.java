package controller;

import model.Patient;
import model.Appointment;
import model.MedicalHistory;
import model.Treatment;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportController {

    public Patient getPatientByName(String name) {
        String query = "SELECT * FROM Patient WHERE name = ? AND userType = 'Patient'";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("birthday"),
                    rs.getString("bloodGroup"),
                    rs.getString("emergencyContact"),
                    rs.getString("address")
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return null;
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment WHERE patientID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("appointmentID"),
                    rs.getInt("patientID"),
                    rs.getInt("surgeonID"),
                    rs.getDate("appointmentDate"),
                    rs.getTime("appointmentTime")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return appointments;
    }

    public List<MedicalHistory> getMedicalHistoryByPatientId(int patientId) {
        List<MedicalHistory> medicalHistory = new ArrayList<>();
        String query = "SELECT * FROM MedicalHistory WHERE patientID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicalHistory.add(new MedicalHistory(
                    rs.getInt("historyID"),
                    rs.getInt("patientID"),
                    rs.getInt("surgeonID"),
                    rs.getInt("appointmentID"),
                    rs.getString("conditions"),
                    rs.getDate("diagnosisDate"),
                    rs.getString("treatment"),
                    rs.getDate("treatmentDate")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return medicalHistory;
    }

    public List<Treatment> getTreatmentsByPatientId(int patientId) {
        List<Treatment> treatments = new ArrayList<>();
        String query = "SELECT * FROM Treatment WHERE patientID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                treatments.add(new Treatment(
                    rs.getInt("treatmentID"),
                    rs.getInt("appointmentID"),
                    rs.getInt("surgeonID"),
                    rs.getString("treatmentDetails"),
                    rs.getDate("treatmentDate")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return treatments;
    }
}
