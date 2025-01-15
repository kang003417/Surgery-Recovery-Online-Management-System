package view;

import controller.SurgeonController;
import model.Surgeon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurgeonProfilePanel extends JPanel {

    private JTextField idField;
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField departmentField;
    private JButton saveButton;

    private SurgeonController surgeonController;
    private int surgeonId;

    public SurgeonProfilePanel(int surgeonId) {
        this.surgeonId = surgeonId;
        surgeonController = new SurgeonController();
        setLayout(new BorderLayout(20, 20));

        // Title
        JLabel titleLabel = new JLabel("Surgeon Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(createLabeledField("Surgeon ID:", idField = new JTextField(20), false));
        formPanel.add(createLabeledField("Username:", usernameField = new JTextField(20), true));
        formPanel.add(createLabeledField("Email:", emailField = new JTextField(20), true));
        formPanel.add(createLabeledField("Phone:", phoneField = new JTextField(20), true));
        formPanel.add(createLabeledField("Department:", departmentField = new JTextField(20), true));

        saveButton = new JButton("SAVE");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);

        // Load the surgeon profile
        loadSurgeonProfile();

        // Save button action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSurgeonProfile();
            }
        });
    }

    private void loadSurgeonProfile() {
        Surgeon surgeon = surgeonController.getSurgeonProfile(surgeonId);
        if (surgeon != null) {
            idField.setText(String.valueOf(surgeon.getSurgeonID()));
            usernameField.setText(surgeon.getUsername());
            emailField.setText(surgeon.getEmail());
            phoneField.setText(surgeon.getPhone());
            departmentField.setText(surgeon.getDepartment());
        }
    }

    private void saveSurgeonProfile() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String department = departmentField.getText().trim();

        if (surgeonController.updateSurgeonProfile(surgeonId, username, email, phone, department)) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update profile. Please try again.");
        }
    }

    private JPanel createLabeledField(String labelText, JTextField textField, boolean editable) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        textField.setEditable(editable);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        return panel;
    }
}
