package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyAppointmentsPanel extends JPanel {

    private int patientId;
    private JTable appointmentsTable;
    private DefaultTableModel tableModel;

    public MyAppointmentsPanel(int patientId) {
        this.patientId = patientId;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My Appointments");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Table columns
        String[] columnNames = {"Appointment ID", "Date", "Time", "Surgeon ID"};

        // Table model
        tableModel = new DefaultTableModel(columnNames, 0);
        appointmentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);

        add(scrollPane, BorderLayout.CENTER);

        // Load appointments
        loadAppointments();
    }

    private void loadAppointments() {
        AppointmentController controller = new AppointmentController();
        List<Appointment> appointments = controller.getAppointmentsForPatient(patientId);

        for (Appointment appointment : appointments) {
            Object[] row = {
                appointment.getAppointmentID(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getSurgeonID()
            };
            tableModel.addRow(row);
        }
    }
}
