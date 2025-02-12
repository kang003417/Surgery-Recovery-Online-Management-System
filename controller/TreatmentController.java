package controller;

import util.DatabaseUtil;
import model.Treatment;
import java.sql.*;

public class TreatmentController {
    public boolean addTreatment(int appointmentID, int surgeonID, String treatmentDetails) {
        String query = "INSERT INTO Treatment (appointmentID, surgeonID, treatmentDetails, treatmentDate) VALUES (?, ?, ?, NOW())";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentID);
            stmt.setInt(2, surgeonID);
            stmt.setString(3, treatmentDetails);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ SQL Error in addTreatment: " + e.getMessage());
            return false;
        }
    }
}
