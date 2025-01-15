package view;

import controller.RegistrationController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField phoneField;
    private JComboBox<String> userTypeDropdown;
    private JComboBox<String> bloodGroupDropdown;
    private JTextField birthDateField;
    private JComboBox<String> genderDropdown;
    private JTextField postcodeField;

    public RegistrationView() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        // Create panel for form fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2, 10, 10));

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(20);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        // Blood Group
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupDropdown = new JComboBox<>(new String[]{"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"});
        formPanel.add(bloodGroupLabel);
        formPanel.add(bloodGroupDropdown);

        // Birth Date
        JLabel birthDateLabel = new JLabel("Birth Date (YYYY-MM-DD):");
        birthDateField = new JTextField(20);
        formPanel.add(birthDateLabel);
        formPanel.add(birthDateField);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderDropdown = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        formPanel.add(genderLabel);
        formPanel.add(genderDropdown);
        
        JLabel postcodeLabel = new JLabel("Postcode:");
        postcodeField = new JTextField(20);
        formPanel.add(postcodeLabel);
        formPanel.add(postcodeField);
        
        // User Type
        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeDropdown = new JComboBox<>(new String[]{"Patient", "Surgeon", "Admin"});
        formPanel.add(userTypeLabel);
        formPanel.add(userTypeDropdown);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        // Add formPanel and buttonPanel to the frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Register Action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String bloodGroup = bloodGroupDropdown.getSelectedItem().toString();
                String birthDate = birthDateField.getText().trim();
                String gender = genderDropdown.getSelectedItem().toString();
                String postcode = postcodeField.getText().trim();
                String userType = userTypeDropdown.getSelectedItem().toString();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || bloodGroup.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || postcode.isEmpty() || userType.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                    return;
                }

                RegistrationController controller = new RegistrationController();
                boolean isRegistered = controller.registerUser(email, password, username, phone, birthDate, bloodGroup, gender, postcode, userType);

                if (isRegistered) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    new LoginView(); // Return to login page
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. Try again.");
                }
            }
        });

        // Back Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView(); // Return to login page
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
