package view;

import controller.ProfileController;
import model.Patient;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    private JTextField usernameField, phoneField, emailField, birthDateField, bloodGroupField, emergencyContactField, addressField, postcodeField;
    private int userId;

    public ProfilePanel(int userId) {
        this.userId = userId;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usernameField = new JTextField(15);
        phoneField = new JTextField(15);
        emailField = new JTextField(15);
        birthDateField = new JTextField(15);
        bloodGroupField = new JTextField(15);
        emergencyContactField = new JTextField(15);
        addressField = new JTextField(15);
        postcodeField = new JTextField(15);

        add(createLabeledField("Username:", usernameField)); 
        add(createLabeledField("Phone Number:", phoneField)); 
        add(createLabeledField("Email:", emailField)); 
        add(createLabeledField("Birth Date (yyyy-mm-dd):", birthDateField)); 
        add(createLabeledField("Blood Group:", bloodGroupField)); 
        add(createLabeledField("Emergency Contact:", emergencyContactField)); 
        add(createLabeledField("Address:", addressField)); 
        add(createLabeledField("Postcode:", postcodeField)); 
        
        JButton saveButton = new JButton("SAVE"); 
        saveButton.addActionListener(e -> saveProfile()); 
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        add(Box.createRigidArea(new Dimension(0, 10))); 
        add(saveButton);
        
        // Load the patient's profile data
        loadPatientProfile(userId);
    }
    
    private JPanel createLabeledField(String labelText, JTextField textField) { 
        JPanel panel = new JPanel(); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); 
        JLabel label = new JLabel(labelText); 
        label.setPreferredSize(new Dimension(150, 25)); 
        panel.add(label); 
        panel.add(textField); 
        return panel;
    }
    private void loadPatientProfile(int userId) {
        ProfileController controller = new ProfileController();
        Patient patient = controller.getPatientProfile(userId);
        if (patient != null) {
            usernameField.setText(patient.getName());
            phoneField.setText(patient.getPhone());
            emailField.setText(patient.getEmail());
            birthDateField.setText(patient.getBirthday());
            bloodGroupField.setText(patient.getBloodGroup());
            emergencyContactField.setText(patient.getEmergencyContact());
            addressField.setText(patient.getAddress());
            postcodeField.setText(patient.getPostcode());
        }
    }

    private void saveProfile() {
        String username = usernameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String bloodGroup = bloodGroupField.getText().trim();
        String emergencyContact = emergencyContactField.getText().trim();
        String address = addressField.getText().trim();
        String postcode = postcodeField.getText().trim();

        if (username.isEmpty() || phone.isEmpty() || email.isEmpty() || birthDate.isEmpty() || bloodGroup.isEmpty() || emergencyContact.isEmpty() || address.isEmpty() || postcode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!");
            return;
        }

        ProfileController controller = new ProfileController();
        Patient patient = new Patient(userId, username, phone, email, birthDate, bloodGroup, emergencyContact, address, postcode);
        boolean updated = controller.updatePatientProfile(patient);

        if (updated) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update profile.");
        }
    }
}
