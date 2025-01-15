package view;

import controller.AdminController;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminManageUserPanel extends JPanel {

    private JTable userTable;
    private DefaultTableModel tableModel;
    private AdminController adminController;
    private JTextField nameField;
    private JComboBox<String> bloodGroupField;
    private JTextField phoneField;
    private JTextField birthDateField;
    private JTextField emailField;
    private JTextField emergencyContactField;
    private JTextField addressField;
    private JTextField postcodeField;
    private JButton saveButton;
    private JButton deleteUserButton;
    private int selectedUserId = -1;

    public AdminManageUserPanel() {
        adminController = new AdminController();
        setLayout(new BorderLayout(20, 20));

        // Title
        JLabel titleLabel = new JLabel("Manage Users", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(220, 220, 220));

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"User ID", "Username", "Email", "Phone", "User Type"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Load user data
        loadUserData();

        add(tablePanel, BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(createLabeledField("Name:", nameField = new JTextField(20), true));
        formPanel.add(createLabeledField("Blood Group:", bloodGroupField = new JComboBox<>(new String[]{"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"}), true));
        formPanel.add(createLabeledField("Phone Number:", phoneField = new JTextField(20), true));
        formPanel.add(createLabeledField("Birth Date (YYYY-MM-DD):", birthDateField = new JTextField(20), true));
        formPanel.add(createLabeledField("Email:", emailField = new JTextField(20), true));
        formPanel.add(createLabeledField("Emergency Contact:", emergencyContactField = new JTextField(20), true));
        formPanel.add(createLabeledField("Address:", addressField = new JTextField(20), true));
        formPanel.add(createLabeledField("Postcode:", postcodeField = new JTextField(20), true));

        saveButton = new JButton("SAVE");
        deleteUserButton = new JButton("DELETE ACCOUNT");

        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(saveButton);
        formPanel.add(deleteUserButton);

        add(formPanel, BorderLayout.EAST);

        // Table Selection Listener
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        selectedUserId = (int) tableModel.getValueAt(selectedRow, 0);
                        User user = adminController.getUserById(selectedUserId);
                        if (user != null) {
                            nameField.setText(user.getUsername());
                            bloodGroupField.setSelectedItem(user.getBloodGroup());
                            phoneField.setText(user.getPhone());
                            birthDateField.setText(user.getBirthDate());
                            emailField.setText(user.getEmail());
                            // Assuming emergencyContact and address are not stored in the User table, set default values
                            emergencyContactField.setText("N/A");
                            addressField.setText("N/A");
                            postcodeField.setText(user.getPostcode());
                        }
                    }
                }
            }
        });

        // Save Button Action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUserId >= 0) {
                    String name = nameField.getText().trim();
                    String bloodGroup = bloodGroupField.getSelectedItem().toString();
                    String phone = phoneField.getText().trim();
                    String birthDate = birthDateField.getText().trim();
                    String email = emailField.getText().trim();
                    String emergencyContact = emergencyContactField.getText().trim();
                    String address = addressField.getText().trim();
                    String postcode = postcodeField.getText().trim();

                    if (adminController.updateUserProfile(selectedUserId, name, bloodGroup, phone, birthDate, email, emergencyContact, address, postcode)) {
                        JOptionPane.showMessageDialog(null, "User profile updated successfully!");
                        loadUserData(); // Reload user data to reflect changes
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update user profile. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user to update.");
                }
            }
        });

        // Delete User Button Action
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedUserId >= 0) {
                    if (adminController.deleteUser(selectedUserId)) {
                        JOptionPane.showMessageDialog(null, "User deleted successfully!");
                        loadUserData(); // Reload user data to reflect changes
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete user. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user to delete.");
                }
            }
        });
    }

    private void loadUserData() {
        tableModel.setRowCount(0); // Clear existing data
        List<User> users = adminController.getAllUsers();
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getUserId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getUserType(), user.getPostcode()});
        }
    }

    private JPanel createLabeledField(String labelText, Component field, boolean editable) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        if (field instanceof JTextField) {
            ((JTextField) field).setEditable(editable);
        } else if (field instanceof JComboBox) {
            ((JComboBox<?>) field).setEnabled(editable);
        }
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        return panel;
    }
}
