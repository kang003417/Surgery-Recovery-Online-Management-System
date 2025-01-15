package view;

import javax.swing.*;
import java.awt.*;
import view.ViewAppointmentsPanel;
public class SurgeonMainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int surgeonId;
    private int patientId = 1; // Example patient ID; replace with actual patient ID logic

    public SurgeonMainView(int surgeonId) {
        this.surgeonId = surgeonId;
        setTitle("ABC Healthcare - Surgeon Portal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create navigation panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(220, 220, 220));

        JLabel title = new JLabel("ABC Healthcare - Surgeon");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(title);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton profileButton = new JButton("Profile");
        JButton chatButton = new JButton("Chat with Patient");
        JButton viewAppointmentsButton = new JButton("View Appointments");
        JButton patientRecordButton = new JButton("Patient Record");
        JButton logoutButton = new JButton("Log out");

        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAppointmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        patientRecordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        navPanel.add(profileButton);
        navPanel.add(chatButton);
        navPanel.add(viewAppointmentsButton);
        navPanel.add(patientRecordButton);
        navPanel.add(Box.createVerticalGlue());
        navPanel.add(logoutButton);

        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different panels to the main panel
        mainPanel.add(new ProfilePanel(surgeonId), "Profile");
        mainPanel.add(new SurgeonChatView(surgeonId, patientId), "Chat with Patient"); // Pass both surgeonId and patientId
        mainPanel.add(new ViewAppointmentsPanel(surgeonId), "View Appointments");
        mainPanel.add(new PatientRecordPanel(), "Patient Record");
        add(navPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners
        profileButton.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));
        chatButton.addActionListener(e -> cardLayout.show(mainPanel, "Chat with Patient"));
        viewAppointmentsButton.addActionListener(e -> cardLayout.show(mainPanel, "View Appointments"));
        patientRecordButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient Record"));
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
        new SurgeonMainView(1); // Example surgeonId, replace with actual surgeon ID
    }
}
