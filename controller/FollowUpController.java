package controller;

import model.FollowUpAppointment;
import model.Appointment;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowUpController {

    public List<FollowUpAppointment> getFollowUpAppointmentsByAppointmentID(int appointmentID) {
        List<FollowUpAppointment> followUpAppointments = new ArrayList<>();
        String query = "SELECT * FROM followupappointment WHERE appointmentID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                followUpAppointments.add(new FollowUpAppointment(
                    rs.getInt("followUpID"),
                    rs.getInt("appointmentID"),
                    rs.getDate("followUpDate"),
                    rs.getTime("followUpTime"),
                    rs.getString("surgeonNotes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return followUpAppointments;
    }

    public List<Appointment> getAppointmentsByAppointmentID(int appointmentID) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE appointmentID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("appointmentID"),
                    rs.getInt("patientID"),
                    rs.getString("patientName"),
                    rs.getDate("appointmentDate"),
                    rs.getTime("appointmentTime"),
                    rs.getString("surgeon")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return appointments;
    }

    public void saveFollowUpAppointment(FollowUpAppointment followUpAppointment) {
        String query = "INSERT INTO followupappointment (followUpID, appointmentID, followUpDate, followUpTime, surgeonNotes) VALUES (?, ?, ?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE followUpDate = VALUES(followUpDate), followUpTime = VALUES(followUpTime), surgeonNotes = VALUES(surgeonNotes)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, followUpAppointment.getFollowUpID());
            stmt.setInt(2, followUpAppointment.getAppointmentID());
            stmt.setDate(3, followUpAppointment.getFollowUpDate());
            stmt.setTime(4, followUpAppointment.getFollowUpTime());
            stmt.setString(5, followUpAppointment.getSurgeonNotes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
    }

    public void updatePatientAppointment(FollowUpAppointment followUpAppointment) {
        String query = "UPDATE appointment SET appointmentDate = ?, appointmentTime = ?, surgeonNotes = ? WHERE appointmentID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, followUpAppointment.getFollowUpDate());
            stmt.setTime(2, followUpAppointment.getFollowUpTime());
            stmt.setString(3, followUpAppointment.getSurgeonNotes());
            stmt.setInt(4, followUpAppointment.getAppointmentID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
    }
}
