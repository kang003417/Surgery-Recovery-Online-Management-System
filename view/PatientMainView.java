package view;

import javax.swing.*;
import java.awt.*;

public class PatientMainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int userId;
    private int surgeonId = 1; // Example surgeon ID; replace with actual surgeon ID logic

    public PatientMainView(int userId) {
        this.userId = userId;
        setTitle("ABC Healthcare");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create navigation panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("ABC Healthcare");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(title);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton profileButton = new JButton("Profile");
        JButton scheduleButton = new JButton("Schedule appointment");
        JButton appointmentsButton = new JButton("My appointment");
        JButton paymentButton = new JButton("Make payment");
        JButton chatButton = new JButton("Chat");
        JButton findClinicButton = new JButton("Find Nearest Clinic");
        JButton logoutButton = new JButton("Log out");

        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scheduleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        appointmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        findClinicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        navPanel.add(profileButton);
        navPanel.add(scheduleButton);
        navPanel.add(appointmentsButton);
        navPanel.add(paymentButton);
        navPanel.add(chatButton);
        navPanel.add(findClinicButton);
        navPanel.add(Box.createVerticalGlue());
        navPanel.add(logoutButton);

        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different panels to the main panel
        mainPanel.add(new ProfilePanel(userId), "Profile");
        mainPanel.add(new ScheduleAppointmentPanel(userId, surgeonId), "Schedule Appointment");
        mainPanel.add(new MyAppointmentsPanel(userId), "My Appointments");
        mainPanel.add(new PaymentMethodPanel(userId), "Make Payment"); // Pass userId to the PaymentMethodPanel constructor
        mainPanel.add(new PatientChatView(userId, surgeonId), "Chat"); // Pass both userId and surgeonId to PatientChatView
        mainPanel.add(new FindNearestClinicPanel(userId), "Find Nearest Clinic");

        add(navPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners
        profileButton.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));
        scheduleButton.addActionListener(e -> cardLayout.show(mainPanel, "Schedule Appointment"));
        appointmentsButton.addActionListener(e -> cardLayout.show(mainPanel, "My Appointments"));
        paymentButton.addActionListener(e -> cardLayout.show(mainPanel, "Make Payment"));
        chatButton.addActionListener(e -> cardLayout.show(mainPanel, "Chat"));
        findClinicButton.addActionListener(e -> cardLayout.show(mainPanel, "Find Nearest Clinic"));
        logoutButton.addActionListener(e -> logout());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void logout() {
        // Close the current window
        dispose();
        
        // Open the LoginView
        new LoginView();
    }

    public static void main(String[] args) {
        new PatientMainView(1); // Example userId, replace with actual user ID
    }
}
