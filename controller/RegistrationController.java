package controller;

import util.DatabaseUtil;
import java.sql.*;

public class RegistrationController {

    public boolean registerUser(String email, String password, String username, String phone, String birthDate, String bloodGroup, String gender, String postcode, String userType) {
        String insertUserQuery = "INSERT INTO User (username, password, email, phone, userType, bloodGroup, birthDate, gender, postcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
             
            connection.setAutoCommit(false);

            // Insert into User table
            stmt.setString(1, username);
            stmt.setString(2, password); // Ensure password is hashed and salted
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, userType);
            stmt.setString(6, bloodGroup);
            stmt.setDate(7, Date.valueOf(birthDate)); // Convert birthDate to SQL date
            stmt.setString(8, gender);
            stmt.setString(9, postcode);
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);

                        // Distribute data to specific tables based on userType
                        boolean distributionSuccess = false;
                        switch (userType) {
                            case "Patient":
                                distributionSuccess = insertPatient(connection, userId, bloodGroup, birthDate, "", "", postcode);
                                break;
                            case "Surgeon":
                                distributionSuccess = insertSurgeon(connection, userId, username, email, phone, "General");
                                break;
                            case "Admin":
                                distributionSuccess = insertAdmin(connection, userId, username, email, phone);
                                break;
                        }

                        if (distributionSuccess) {
                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean insertPatient(Connection connection, int userId, String bloodGroup, String birthDate, String emergencyContact, String address, String postcode) {
        String insertPatientQuery = "INSERT INTO Patient (userID, bloodGroup, birthDate, emergencyContact, address, postcode) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(insertPatientQuery)) {
             
            stmt.setInt(1, userId);
            stmt.setString(2, bloodGroup);
            stmt.setDate(3, Date.valueOf(birthDate)); // Convert birthDate to SQL date
            stmt.setString(4, emergencyContact);
            stmt.setString(5, address);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean insertSurgeon(Connection connection, int userId, String username, String email, String phone, String department) {
        String insertSurgeonQuery = "INSERT INTO Surgeon (userID, username, email, phone, department) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(insertSurgeonQuery)) {
             
            stmt.setInt(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, department);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean insertAdmin(Connection connection, int userId, String username, String email, String phone) {
        String insertAdminQuery = "INSERT INTO Admin (userID, username, email, phone) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(insertAdminQuery)) {
             
            stmt.setInt(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
