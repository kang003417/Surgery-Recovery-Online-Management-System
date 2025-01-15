package view;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import controller.AppointmentController;

public class ScheduleAppointmentPanel extends JPanel {

    private JComboBox<String> timeComboBox;
    private JCalendar calendar;
    private int patientId;
    private int surgeonId;

    public ScheduleAppointmentPanel(int patientId, int surgeonId) {
        this.patientId = patientId;
        this.surgeonId = surgeonId;
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Schedule Appointment", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // JCalendar Panel
        calendar = new JCalendar();
        add(calendar, BorderLayout.WEST);

        // Time Selection Panel
        JPanel timePanel = new JPanel(new GridLayout(12, 1, 5, 5));
        timePanel.setBorder(BorderFactory.createTitledBorder("Available Time"));

        String[] times = {"09:00:00", "09:30:00", "10:00:00", "10:30:00", "12:00:00", "12:30:00",
                          "13:30:00", "14:00:00", "15:00:00", "16:30:00", "17:00:00", "17:30:00"};
        timeComboBox = new JComboBox<>(times);
        timePanel.add(timeComboBox);

        add(timePanel, BorderLayout.CENTER);

        // Book Now Button
        JButton bookButton = new JButton("Book Now");
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.setBackground(new Color(60, 179, 113)); // Medium Sea Green background
        bookButton.setForeground(Color.WHITE); // White text

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String selectedDate = dateFormat.format(calendar.getDate());
                    String selectedTime = (String) timeComboBox.getSelectedItem();

                    if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled!");
                        return;
                    }

                    AppointmentController controller = new AppointmentController();
                    boolean isScheduled = controller.scheduleAppointment(patientId, surgeonId, Date.valueOf(selectedDate), Time.valueOf(selectedTime));

                    if (isScheduled) {
                        JOptionPane.showMessageDialog(null, "Appointment scheduled successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to schedule appointment. Try again.");
                    }
                } catch (Exception ex) {
                    System.err.println("Exception occurred: " + ex.getMessage());
                    ex.printStackTrace(); // Print stack trace for debugging
                    JOptionPane.showMessageDialog(null, "Error scheduling appointment. Please check the entered details.");
                }
            }
        });

        add(bookButton, BorderLayout.SOUTH);
    }
}
