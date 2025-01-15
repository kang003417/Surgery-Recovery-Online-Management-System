package view;

import controller.FollowUpController;
import model.FollowUpAppointment;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ScheduleFollowUpPanel extends JPanel {

    private JTextField searchField;
    private JLabel appointmentIDLabel, followUpDateLabel, followUpTimeLabel, surgeonNotesLabel;
    private JTable followUpTable, appointmentTable;
    private DefaultTableModel followUpTableModel, appointmentTableModel;
    private JButton saveButton, confirmButton, rescheduleButton;

    public ScheduleFollowUpPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel followUpInfoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JPanel tablePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel title = new JLabel("Schedule Follow-Up Appointment", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Search bar
        searchField = new JTextField("Search Appointment ID...");
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search Appointment ID...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search Appointment ID...");
                }
            }
        });

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentIDStr = searchField.getText().trim();
                if (!appointmentIDStr.isEmpty() && !appointmentIDStr.equals("Search Appointment ID...")) {
                    try {
                        int appointmentID = Integer.parseInt(appointmentIDStr);
                        searchFollowUpAppointments(appointmentID);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid appointment ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter an appointment ID.");
                }
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Follow-up info labels
        appointmentIDLabel = new JLabel("Appointment ID: ");
        followUpDateLabel = new JLabel("Follow-Up Date: ");
        followUpTimeLabel = new JLabel("Follow-Up Time: ");
        surgeonNotesLabel = new JLabel("Surgeon Notes: ");


        followUpInfoPanel.add(appointmentIDLabel);

        followUpInfoPanel.add(followUpDateLabel);

        followUpInfoPanel.add(followUpTimeLabel);

        followUpInfoPanel.add(surgeonNotesLabel);

        // Follow-up table
        String[] followUpColumns = {"Follow-Up ID", "Appointment ID", "Follow-Up Date", "Follow-Up Time", "Surgeon Notes"};
        followUpTableModel = new DefaultTableModel(followUpColumns, 0);
        followUpTable = new JTable(followUpTableModel);
        JScrollPane followUpScrollPane = new JScrollPane(followUpTable);
        followUpScrollPane.setBorder(BorderFactory.createTitledBorder("Follow-Up Appointments"));

        // Appointment table
        String[] appointmentColumns = {"Appointment ID", "Patient Name", "Appointment Date", "Appointment Time", "Surgeon"};
        appointmentTableModel = new DefaultTableModel(appointmentColumns, 0);
        appointmentTable = new JTable(appointmentTableModel);
        JScrollPane appointmentScrollPane = new JScrollPane(appointmentTable);
        appointmentScrollPane.setBorder(BorderFactory.createTitledBorder("Appointments"));

        tablePanel.add(appointmentScrollPane);
        tablePanel.add(followUpScrollPane);

        // Buttons
        confirmButton = new JButton("Confirm Appointment");
        rescheduleButton = new JButton("Reschedule Appointment");
        saveButton = new JButton("Save");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Appointment confirmed.");
            }
        });

        rescheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Appointment rescheduled.");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFollowUpAppointments();
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(rescheduleButton);
        buttonPanel.add(saveButton);

        add(topPanel, BorderLayout.NORTH);
        add(followUpInfoPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void searchFollowUpAppointments(int appointmentID) {
        FollowUpController controller = new FollowUpController();
        List<FollowUpAppointment> followUpAppointments = controller.getFollowUpAppointmentsByAppointmentID(appointmentID);
        followUpTableModel.setRowCount(0); // Clear existing rows
        for (FollowUpAppointment appointment : followUpAppointments) {
            Object[] row = {
                appointment.getFollowUpID(),
                appointment.getAppointmentID(),
                appointment.getFollowUpDate(),
                appointment.getFollowUpTime(),
                appointment.getSurgeonNotes()
            };
            followUpTableModel.addRow(row);
        }

        List<Appointment> appointments = controller.getAppointmentsByAppointmentID(appointmentID);
        appointmentTableModel.setRowCount(0); // Clear existing rows
        for (Appointment appointment : appointments) {
            Object[] row = {
                appointment.getAppointmentID(),
                appointment.getPatientName(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getSurgeon()
            };
            appointmentTableModel.addRow(row);
        }
    }

    private void saveFollowUpAppointments() {
        FollowUpController controller = new FollowUpController();
        for (int i = 0; i < followUpTableModel.getRowCount(); i++) {
            int followUpID = (int) followUpTableModel.getValueAt(i, 0);
            int appointmentID = (int) followUpTableModel.getValueAt(i, 1);
            Date followUpDate = (Date) followUpTableModel.getValueAt(i, 2);
            Time followUpTime = (Time) followUpTableModel.getValueAt(i, 3);
            String surgeonNotes = (String) followUpTableModel.getValueAt(i, 4);

            FollowUpAppointment followUpAppointment = new FollowUpAppointment(followUpID, appointmentID, followUpDate, followUpTime, surgeonNotes);
            controller.saveFollowUpAppointment(followUpAppointment);
            controller.updatePatientAppointment(followUpAppointment);
        }

        JOptionPane.showMessageDialog(null, "Follow-up appointments saved.");
        searchFollowUpAppointments(Integer.parseInt(searchField.getText().trim()));
    }
}
