package controller;

import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageController {
    public static void saveMessage(String sender, String receiver, String message) {

        String query = "INSERT INTO Messages (sender, receiver, message, timestamp) VALUES (?, ?, ?, NOW())";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, message);
            stmt.executeUpdate();
            System.out.println("✅ Message saved: " + sender + " → " + receiver + ": " + message);
            
        } catch (SQLException e) {
            System.err.println("❌ Error saving message: " + e.getMessage());
        }
    }

    public static List<String> getChatHistory(String sender, String receiver) {
        List<String> chatHistory = new ArrayList<>();
        String query = "SELECT sender, message, timestamp FROM Messages WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?) ORDER BY timestamp ASC";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, receiver);
            stmt.setString(4, sender);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String chatLine = rs.getString("sender") + ": " + rs.getString("message");
                chatHistory.add(chatLine);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching chat history: " );
            e.printStackTrace();
        }
        return chatHistory;
    }
    
    public static List<String> getAssignedPatients(int surgeonUserId) {
        List<String> patientList = new ArrayList<>();

        String query = "SELECT p.userID, p.username FROM Patient p";

        try (Connection connection = DatabaseUtil.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int patientUserId = rs.getInt("userID");
            String patientName = rs.getString("username");
            patientList.add(patientName + "_" + patientUserId);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return patientList;
    }

}
