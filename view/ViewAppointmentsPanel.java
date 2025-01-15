package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewAppointmentsPanel extends JPanel {

    private int surgeonId;
    private JTable appointmentsTable;
    private DefaultTableModel tableModel;

    public ViewAppointmentsPanel(int surgeonId) {
        this.surgeonId = surgeonId;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Surgeon Appointments");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Table columns
        String[] columnNames = {"Appointment ID", "Date", "Time", "Patient ID", "Actions"};

        // Table model
        tableModel = new DefaultTableModel(columnNames, 0);
        appointmentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);

        add(scrollPane, BorderLayout.CENTER);

        // Load appointments
        loadAppointments();

        // Actions Panel
        JPanel actionsPanel = new JPanel();
        JButton rescheduleButton = new JButton("Reschedule");
        JButton cancelButton = new JButton("Cancel");

        actionsPanel.add(rescheduleButton);
        actionsPanel.add(cancelButton);
        add(actionsPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        rescheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = appointmentsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int appointmentID = (int) tableModel.getValueAt(selectedRow, 0);
                    rescheduleAppointment(appointmentID);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to reschedule.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = appointmentsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int appointmentID = (int) tableModel.getValueAt(selectedRow, 0);
                    cancelAppointment(appointmentID);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to cancel.");
                }
            }
        });
    }

    private void loadAppointments() {
        AppointmentController controller = new AppointmentController();
        List<Appointment> appointments = controller.getAppointmentsForSurgeon(surgeonId);

        for (Appointment appointment : appointments) {
            Object[] row = {
                appointment.getAppointmentID(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getPatientID(),
                "Reschedule / Cancel"
            };
            tableModel.addRow(row);
        }
    }

    private void rescheduleAppointment(int appointmentID) {
        // Implementation for rescheduling an appointment
        JOptionPane.showMessageDialog(null, "Reschedule functionality is not yet implemented.");
    }

    private void cancelAppointment(int appointmentID) {
        AppointmentController controller = new AppointmentController();
        boolean success = controller.cancelAppointment(appointmentID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Appointment cancelled successfully.");
            // Refresh the table
            tableModel.setRowCount(0);
            loadAppointments();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to cancel the appointment.");
        }
    }
}
