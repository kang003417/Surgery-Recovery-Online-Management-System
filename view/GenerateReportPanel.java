package view;

import controller.ReportController;
import model.Patient;
import model.Appointment;
import model.MedicalHistory;
import model.Treatment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GenerateReportPanel extends JPanel {

    private JTextField searchField;
    private JLabel nameLabel, phoneLabel, emailLabel, birthdayLabel, bloodGroupLabel, emergencyContactLabel, addressLabel;
    private JTable appointmentTable, medicalHistoryTable, treatmentTable;
    private DefaultTableModel appointmentTableModel, medicalHistoryTableModel, treatmentTableModel;

    public GenerateReportPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel patientInfoPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        JPanel reportPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JLabel title = new JLabel("ABC Healthcare", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Search bar
        searchField = new JTextField("Search Patient Name...");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = searchField.getText().trim();
                if (!patientName.isEmpty()) {
                    generateReport(patientName);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a patient name.");
                }
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Patient info labels
        nameLabel = new JLabel("Name: ");
        phoneLabel = new JLabel("Phone: ");
        emailLabel = new JLabel("Email: ");
        birthdayLabel = new JLabel("Birthday: ");
        bloodGroupLabel = new JLabel("Blood Group: ");
        emergencyContactLabel = new JLabel("Emergency Contact: ");
        addressLabel = new JLabel("Address: ");

        
        patientInfoPanel.add(nameLabel);
        patientInfoPanel.add(phoneLabel);

        patientInfoPanel.add(emailLabel);
 
        patientInfoPanel.add(birthdayLabel);

        patientInfoPanel.add(bloodGroupLabel);

        patientInfoPanel.add(emergencyContactLabel);

        patientInfoPanel.add(addressLabel);

        // Appointment table
        String[] appointmentColumns = {"Appointment ID", "Date", "Time", "Surgeon"};
        appointmentTableModel = new DefaultTableModel(appointmentColumns, 0);
        appointmentTable = new JTable(appointmentTableModel);
        JScrollPane appointmentScrollPane = new JScrollPane(appointmentTable);
        appointmentScrollPane.setBorder(BorderFactory.createTitledBorder("Appointments"));

        // Medical history table
        String[] medicalHistoryColumns = {"History ID", "Conditions", "Diagnosis Date", "Treatment", "Treatment Date"};
        medicalHistoryTableModel = new DefaultTableModel(medicalHistoryColumns, 0);
        medicalHistoryTable = new JTable(medicalHistoryTableModel);
        JScrollPane medicalHistoryScrollPane = new JScrollPane(medicalHistoryTable);
        medicalHistoryScrollPane.setBorder(BorderFactory.createTitledBorder("Medical History"));

        // Treatment table
        String[] treatmentColumns = {"Treatment ID", "Appointment ID", "Surgeon ID", "Treatment Details", "Treatment Date"};
        treatmentTableModel = new DefaultTableModel(treatmentColumns, 0);
        treatmentTable = new JTable(treatmentTableModel);
        JScrollPane treatmentScrollPane = new JScrollPane(treatmentTable);
        treatmentScrollPane.setBorder(BorderFactory.createTitledBorder("Treatments"));

        reportPanel.add(appointmentScrollPane);
        reportPanel.add(medicalHistoryScrollPane);
        reportPanel.add(treatmentScrollPane);

        add(topPanel, BorderLayout.NORTH);
        add(patientInfoPanel, BorderLayout.WEST);
        add(reportPanel, BorderLayout.CENTER);
    }

    private void generateReport(String patientName) {
        ReportController controller = new ReportController();
        Patient patient = controller.getPatientByName(patientName);
        if (patient != null) {
            nameLabel.setText("Name: " + patient.getName());
            phoneLabel.setText("Phone: " + patient.getPhone());
            emailLabel.setText("Email: " + patient.getEmail());
            birthdayLabel.setText("Birthday: " + patient.getBirthday());
            bloodGroupLabel.setText("Blood Group: " + patient.getBloodGroup());
            emergencyContactLabel.setText("Emergency Contact: " + patient.getEmergencyContact());
            addressLabel.setText("Address: " + patient.getAddress());

            List<Appointment> appointments = controller.getAppointmentsByPatientId(patient.getUserId());
            appointmentTableModel.setRowCount(0); // Clear existing rows
            for (Appointment appointment : appointments) {
                Object[] row = {
                    appointment.getAppointmentID(),
                    appointment.getAppointmentDate(),
                    appointment.getAppointmentTime(),
                    appointment.getSurgeonID()
                };
                appointmentTableModel.addRow(row);
            }

            List<MedicalHistory> medicalHistory = controller.getMedicalHistoryByPatientId(patient.getUserId());
            medicalHistoryTableModel.setRowCount(0); // Clear existing rows
            for (MedicalHistory history : medicalHistory) {
                Object[] row = {
                    history.getHistoryID(),
                    history.getConditions(),
                    history.getDiagnosisDate(),
                    history.getTreatment(),
                    history.getTreatmentDate()
                };
                medicalHistoryTableModel.addRow(row);
            }

            List<Treatment> treatments = controller.getTreatmentsByPatientId(patient.getUserId());
            treatmentTableModel.setRowCount(0); // Clear existing rows
            for (Treatment treatment : treatments) {
                Object[] row = {
                    treatment.getTreatmentID(),
                    treatment.getAppointmentID(),
                    treatment.getSurgeonID(),
                    treatment.getTreatmentDetails(),
                    treatment.getTreatmentDate()
                };
                treatmentTableModel.addRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Patient not found");
        }
    }
}
