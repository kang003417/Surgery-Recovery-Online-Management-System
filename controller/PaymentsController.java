package controller;

import model.Payments;
import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentsController {

    public static List<Payments> getAllPayments() {
        List<Payments> paymentsList = new ArrayList<>();
        String query = "SELECT p.paymentID, u.userID AS patientID, u.username AS patientName, " +
                       "u.email AS patientEmail, p.paymentStatus, p.dateOfPayment, " +
                       "p.treatment, p.amountPaid FROM Payments p " +
                       "JOIN User u ON p.patientID = u.userID";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Payments payment = new Payments(
                        rs.getInt("paymentID"),
                        rs.getInt("patientID"),
                        rs.getString("patientName"),
                        rs.getString("patientEmail"),
                        rs.getString("paymentStatus"),
                        rs.getDate("dateOfPayment"),
                        rs.getString("treatment"),
                        rs.getBigDecimal("amountPaid")
                );
                paymentsList.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentsList;
    }
}
