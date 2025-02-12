package controller;

import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    // ✅ Get a list of all patients with user IDs and names
    public static List<String> getAllPatients() {
        List<String> patients = new ArrayList<>();
        String query = "SELECT userID, username FROM User WHERE userType = 'Patient'";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                patients.add(userID + ": " + username); // Format -> "26: John Doe"
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}
