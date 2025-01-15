package view;

import javax.swing.*;
import java.awt.*;

public class AdminMainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int userId;

    public AdminMainView(int userId) {
        this.userId = userId;
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create navigation panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(title);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton manageUsersButton = new JButton("Manage Users");
        JButton generateReportsButton = new JButton("Generate Reports");
        JButton scheduleFollowUpButton = new JButton("Schedule Follow-Up");
        JButton trackPaymentsButton = new JButton("Track Payments");
        JButton logoutButton = new JButton("Log out");

        manageUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateReportsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scheduleFollowUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        trackPaymentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        navPanel.add(manageUsersButton);
        navPanel.add(generateReportsButton);
        navPanel.add(scheduleFollowUpButton);
        navPanel.add(trackPaymentsButton);
        navPanel.add(Box.createVerticalGlue());
        navPanel.add(logoutButton);

        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different panels to the main panel
        mainPanel.add(new AdminManageUserPanel(), "Manage Users");
        mainPanel.add(new GenerateReportPanel(), "Generate Reports");
        mainPanel.add(new ScheduleFollowUpPanel(), "Schedule Follow-Up");
        mainPanel.add(new TrackPaymentPanel(), "Track Payments");

        add(navPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners
        manageUsersButton.addActionListener(e -> cardLayout.show(mainPanel, "Manage Users"));
        generateReportsButton.addActionListener(e -> cardLayout.show(mainPanel, "Generate Reports"));
        scheduleFollowUpButton.addActionListener(e -> cardLayout.show(mainPanel, "Schedule Follow-Up"));
        trackPaymentsButton.addActionListener(e -> cardLayout.show(mainPanel, "Track Payments"));
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
        new AdminMainView(1); // Example userId, replace with actual user ID
    }
}
