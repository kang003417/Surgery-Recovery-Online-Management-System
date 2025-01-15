package view;

import controller.ClinicController;
import model.Clinic;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FindNearestClinicPanel extends JPanel {

    private JComboBox<String> clinicComboBox;

    public FindNearestClinicPanel(int patientID) {
        setLayout(null); // Use absolute positioning
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Find Nearest Clinic", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(150, 20, 200, 30); // Set bounds for the title

        clinicComboBox = new JComboBox<>();
        clinicComboBox.setBounds(150, 60, 150, 25); // Set bounds for the combo box
        loadNearestClinics(patientID);

        add(title);
        add(clinicComboBox);
    }

    private void loadNearestClinics(int patientID) {
        ClinicController controller = new ClinicController();
        List<Clinic> clinics = controller.getNearestClinicsByPatientID(patientID);
        clinicComboBox.removeAllItems();
        for (Clinic clinic : clinics) {
            clinicComboBox.addItem(clinic.getClinicname() + " - " + clinic.getClinicpostcode());
        }
    }
}
