package controller;

import model.FollowUpAppointment;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowUpController {

    public List<FollowUpAppointment> getAllFollowUpAppointments() {
        List<FollowUpAppointment> appointments = new ArrayList<>();
        String query = "SELECT followUpID, appointmentID, followUpDate, followUpTime, surgeonNotes FROM FollowUpAppointment";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FollowUpAppointment appointment = new FollowUpAppointment(
                        rs.getInt("followUpID"),
                        rs.getInt("appointmentID"),
                        rs.getDate("followUpDate"),
                        rs.getTime("followUpTime"),
                        rs.getString("surgeonNotes")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Exception in getAllFollowUpAppointments: " + e.getMessage());
        }
        return appointments;
    }

    public boolean scheduleFollowUp(int appointmentID, Date followUpDate, Time followUpTime, String surgeonNotes) {
        String insertQuery = "INSERT INTO FollowUpAppointment (appointmentID, followUpDate, followUpTime, surgeonNotes) VALUES (?, ?, ?, ?)";
        String updateAppointmentQuery = "UPDATE Appointment SET appointmentDate = ?, appointmentTime = ? WHERE appointmentID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
             PreparedStatement updateStmt = connection.prepareStatement(updateAppointmentQuery)) {

            connection.setAutoCommit(false); // ✅ Start transaction

            // ✅ Step 1: Insert follow-up appointment
            insertStmt.setInt(1, appointmentID);
            insertStmt.setDate(2, followUpDate);
            insertStmt.setTime(3, followUpTime);
            insertStmt.setString(4, surgeonNotes);
            int followUpInserted = insertStmt.executeUpdate();

            // ✅ Step 2: Update original appointment date & time
            updateStmt.setDate(1, followUpDate);
            updateStmt.setTime(2, followUpTime);
            updateStmt.setInt(3, appointmentID);
            int appointmentUpdated = updateStmt.executeUpdate();

            if (followUpInserted > 0 && appointmentUpdated > 0) {
                connection.commit(); // ✅ Commit transaction
                return true;
            } else {
                connection.rollback(); // ❌ Rollback if any step fails
                return false;
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Exception in scheduleFollowUp: " + e.getMessage());
            return false;
        }
    }

    public boolean updateFollowUp(int followUpID, Date followUpDate, Time followUpTime, String surgeonNotes) {
        String updateQuery = "UPDATE FollowUpAppointment SET followUpDate = ?, followUpTime = ?, surgeonNotes = ? WHERE followUpID = ?";
        String updateAppointmentQuery = "UPDATE Appointment SET appointmentDate = ?, appointmentTime = ? WHERE appointmentID = (SELECT appointmentID FROM FollowUpAppointment WHERE followUpID = ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement followUpStmt = connection.prepareStatement(updateQuery);
             PreparedStatement appointmentStmt = connection.prepareStatement(updateAppointmentQuery)) {

            connection.setAutoCommit(false); // ✅ Start transaction

            // ✅ Step 1: Update Follow-up table
            followUpStmt.setDate(1, followUpDate);
            followUpStmt.setTime(2, followUpTime);
            followUpStmt.setString(3, surgeonNotes);
            followUpStmt.setInt(4, followUpID);
            int followUpUpdated = followUpStmt.executeUpdate();

            // ✅ Step 2: Update Appointment table
            appointmentStmt.setDate(1, followUpDate);
            appointmentStmt.setTime(2, followUpTime);
            appointmentStmt.setInt(3, followUpID);
            int appointmentUpdated = appointmentStmt.executeUpdate();

            if (followUpUpdated > 0 && appointmentUpdated > 0) {
                connection.commit(); // ✅ Commit transaction
                return true;
            } else {
                connection.rollback(); // ❌ Rollback if any step fails
                return false;
            }
        } catch (SQLException e) {
            System.err.println("❌ SQL Exception in updateFollowUp: " + e.getMessage());
            return false;
        }
    }
}
