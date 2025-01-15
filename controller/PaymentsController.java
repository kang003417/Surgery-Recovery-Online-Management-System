package controller;

import model.Payments;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentsController {

    public List<Payments> getAllPayments() {
        List<Payments> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                payments.add(new Payments(
                    rs.getInt("paymentID"),
                    rs.getInt("patientID"),
                    rs.getString("patientName"),
                    rs.getString("patientEmail"),
                    rs.getString("paymentStatus"),
                    rs.getDate("dateOfPayment"),
                    rs.getString("treatment"),
                    rs.getBigDecimal("amountPaid")
                ));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return payments;
    }
}
