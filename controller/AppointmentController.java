package controller;

import model.Appointment;
import util.DatabaseUtil;
import view.MyAppointmentsPanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AppointmentController {

    public List<Appointment> getAppointmentsForSurgeon(int userId) {
    List<Appointment> appointments = new ArrayList<>();

    // ✅ Fetch appointments using `userID`, NOT `surgeonID`
    String query = "SELECT a.appointmentID, a.patientID, a.appointmentDate, a.appointmentTime " +
                   "FROM Appointment a " +
                   "JOIN Surgeon s ON a.surgeonID = s.surgeonID " +  // 🔹 Link `Surgeon` table with `Appointment`
                   "WHERE s.userID = ?";  // ✅ Use `userID` instead of `surgeonID`

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {

        stmt.setInt(1, userId); // ✅ Use `userID` (29)
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            appointments.add(new Appointment(
                rs.getInt("appointmentID"),
                userId,
                rs.getInt("patientID"),  // ✅ Keep `patientID`
                rs.getDate("appointmentDate"),
                rs.getTime("appointmentTime")
                  // ✅ Store `userID`, NOT `surgeonID`
            ));
        }
        System.out.println("✅ Total Appointments Found: " + appointments.size());

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("❌ SQL Exception in getAppointmentsForSurgeon: " + e.getMessage());
    }

    return appointments;
}


    public List<Appointment> getAppointmentsForPatient(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        System.out.println("Received patientID: " + patientId);
        // SQL query directly uses patientID
        String query = "SELECT a.*, COALESCE(p.username, 'Unknown') AS patientname " +
                   "FROM appointment AS a " +
                   "LEFT JOIN patient AS p ON a.patientID = p.patientID " +
                   "WHERE a.patientID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, patientId);
        System.out.println("Executing Query for patientID: " + patientId);

        ResultSet rs = stmt.executeQuery();
        boolean hasRecords = rs.next();
        System.out.println("Has Records: " + hasRecords);

        if (hasRecords) {
            do {
                int fetchedPatientID = rs.getInt("patientID");
                System.out.println("Fetched patientID from DB: " + fetchedPatientID);
                Appointment appointment = new Appointment(
                    rs.getInt("appointmentID"),
                    rs.getInt("surgeonID"),
                    rs.getInt("patientID"),
                    rs.getDate("appointmentDate"),
                    rs.getTime("appointmentTime")
                    
            
                );
                System.out.println("Retrieved Appointment: " + appointment);
                appointments.add(appointment);
            } while (rs.next());
        }
    } catch (SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    }

    return appointments;
    }


    public boolean cancelAppointment(int appointmentID) {
        String query = "DELETE FROM Appointment WHERE appointmentID = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }
    
    public boolean scheduleAppointment(int patientId, int surgeonId, Date appointmentDate, Time appointmentTime, MyAppointmentsPanel panel) {
        String query = "INSERT INTO Appointment (patientID, surgeonID, appointmentDate, appointmentTime) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, surgeonId);
            stmt.setDate(3, appointmentDate);
            stmt.setTime(4, appointmentTime);
            

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }
    
    public Integer getSurgeonByAppointment(int appointmentID) {
    String query = "SELECT surgeonID FROM Appointment WHERE appointmentID = ?";
    Integer surgeonID = null; // Use Integer to handle null cases

    try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, appointmentID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            surgeonID = rs.getInt("surgeonID"); // ✅ Fetch surgeonID
        }
    } catch (SQLException e) {
        System.err.println("❌ SQL Exception in getSurgeonByAppointment: " + e.getMessage());
    }

    return surgeonID; // ✅ Returns null if no data found
    }

}
