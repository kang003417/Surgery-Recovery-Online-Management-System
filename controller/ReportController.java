package controller;

import model.Appointment;
import model.MedicalHistory;
import model.Treatment;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportController {

    // ✅ Fetch all appointments for a patient
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT appointmentID, patientID, surgeonID, appointmentDate, appointmentTime " +
                       "FROM Appointment WHERE patientID = ?";

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
            System.err.println("❌ SQL Exception in getAppointmentsByPatient: " + e.getMessage());
        }
        return appointments;
    }

    // ✅ Fetch medical history for a patient
    public List<MedicalHistory> getMedicalHistoryByPatient(int patientId) {
        List<MedicalHistory> history = new ArrayList<>();
        String query = "SELECT historyID, patientID, surgeonID, appointmentID, conditions, diagnosisDate, treatment, treatmentDate " +
                       "FROM MedicalHistory WHERE patientID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                history.add(new MedicalHistory(
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
            System.err.println("❌ SQL Exception in getMedicalHistoryByPatient: " + e.getMessage());
        }
        return history;
    }

    // ✅ Fetch treatments related to a patient
    public List<Treatment> getTreatmentsByPatient(int patientId) {
        List<Treatment> treatments = new ArrayList<>();
        String query = "SELECT treatmentID, appointmentID, surgeonID, treatmentDetails, treatmentDate " +
                       "FROM Treatment WHERE appointmentID IN (SELECT appointmentID FROM Appointment WHERE patientID = ?)";

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
            System.err.println("❌ SQL Exception in getTreatmentsByPatient: " + e.getMessage());
        }
        return treatments;
    }
}
