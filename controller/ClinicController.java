package controller;

import model.Clinic;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicController {

    public List<Clinic> getNearestClinicsByPatientID(int patientID) {
        List<Clinic> clinics = new ArrayList<>();
        String query = "SELECT c.clinicID, c.clinicName, c.clinicAddress, c.clinicPhone, c.clinicPostcode, c.patientPostcode FROM Clinic c JOIN Patient p ON c.clinicPostcode = p.postcode WHERE p.userID = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clinics.add(new Clinic(
                    rs.getInt("clinicID"),
                    rs.getString("clinicName"),
                    rs.getString("clinicAddress"),
                    rs.getString("clinicPhone"),
                    rs.getString("clinicPostcode"),
                    rs.getString("patientPostcode")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return clinics;
    }
}
