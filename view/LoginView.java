package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.LoginController;
import java.sql.*;
import util.DatabaseUtil;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginView() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                LoginController loginController = new LoginController();
                if (loginController.authenticateUser(email, password)) {
                    int userId = getUserIdByEmail(email);
                    String userType = getUserTypeByEmail(email);
                    JOptionPane.showMessageDialog(null, "Login successful!");

                    switch (userType) {
                        case "Patient":
                            new PatientMainView(userId);
                            break;
                        case "Surgeon":
                            new SurgeonMainView(userId);
                            break;
                        case "Admin":
                            new AdminMainView(userId);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Unknown user type.");
                            break;
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationView();
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to get user ID by email
    private int getUserIdByEmail(String email) {
        int userId = -1;
        String query = "SELECT userID FROM User WHERE email = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userID");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return userId;
    }

    // Method to get user type by email
    private String getUserTypeByEmail(String email) {
        String userType = "";
        String query = "SELECT userType FROM User WHERE email = ?";
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userType = rs.getString("userType");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }
        return userType;
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
