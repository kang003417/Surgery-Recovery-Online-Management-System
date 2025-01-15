package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientRecordPanel extends JPanel {

    private JTextField searchField;
    private JLabel nameLabel, ageLabel, genderLabel, diagnosisLabel, surgeonLabel, consultationDateTimeLabel, statusLabel;
    private JTable treatmentProgressTable;
    private JButton saveButton;

    public PatientRecordPanel() {
        setLayout(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel patientInfoPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        JLabel title = new JLabel("Patient Record", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Search bar
        searchField = new JTextField("Search patient name");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement search functionality here
                JOptionPane.showMessageDialog(null, "Patient not found");
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Patient info labels
        nameLabel = new JLabel("Name: Abc");
        ageLabel = new JLabel("Age: 12");
        genderLabel = new JLabel("Gender: Male");
        diagnosisLabel = new JLabel("Diagnosis: diagnosis");
        surgeonLabel = new JLabel("Surgeon: Dr.abc");
        consultationDateTimeLabel = new JLabel("Consultation Date : Time: 12/12/2022 - 03:40 PM");
        statusLabel = new JLabel("Status: active/inactive");

        patientInfoPanel.add(new JLabel("Name:"));
        patientInfoPanel.add(nameLabel);
        patientInfoPanel.add(new JLabel("Age:"));
        patientInfoPanel.add(ageLabel);
        patientInfoPanel.add(new JLabel("Gender:"));
        patientInfoPanel.add(genderLabel);
        patientInfoPanel.add(new JLabel("Diagnosis:"));
        patientInfoPanel.add(diagnosisLabel);
        patientInfoPanel.add(new JLabel("Surgeon:"));
        patientInfoPanel.add(surgeonLabel);
        patientInfoPanel.add(new JLabel("Consultation Date : Time:"));
        patientInfoPanel.add(consultationDateTimeLabel);
        patientInfoPanel.add(new JLabel("Status:"));
        patientInfoPanel.add(statusLabel);

        // Treatment progress table
        String[] columnNames = {"Date of Consultation", "Surgeon", "Diagnosis", "Next Follow-up Date"};
        Object[][] data = {
            {"12/12/2022", "Dr.abc", "diagnosis", "12/12/2022"}
        };
        treatmentProgressTable = new JTable(data, columnNames);
        JScrollPane treatmentProgressScrollPane = new JScrollPane(treatmentProgressTable);
        treatmentProgressScrollPane.setBorder(BorderFactory.createTitledBorder("Treatment Progress"));

        // Save button
        saveButton = new JButton("SAVE");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement save functionality here
                JOptionPane.showMessageDialog(null, "Saved successfully");
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(patientInfoPanel, BorderLayout.CENTER);
        add(treatmentProgressScrollPane, BorderLayout.SOUTH);
        add(saveButton, BorderLayout.EAST);
    }
}
