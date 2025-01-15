package controller;

import model.Payment;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentController {

    public boolean processPayment(Payment payment) {
    String query = "INSERT INTO payment (patientID, amount, paymentDate, paymentMethod, receipt) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, payment.getPatientID());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, payment.getPaymentDate());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getReceipt());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            return false;
        }
    }
}
