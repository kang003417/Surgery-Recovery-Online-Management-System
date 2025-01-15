package controller;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageController {

    public void saveMessage(int senderID, int receiverID, String messageText) {
        String query = "INSERT INTO Message (senderID, receiverID, messageText, messageDateTime) VALUES (?, ?, ?, NOW())";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, senderID);
            stmt.setInt(2, receiverID);
            stmt.setString(3, messageText);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
    }
}
