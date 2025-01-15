package view;

import controller.PaymentsController;
import model.Payments;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TrackPaymentPanel extends JPanel {

    private JTable paymentTable;
    private DefaultTableModel paymentTableModel;

    public TrackPaymentPanel() {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Track Payments", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Payment table
        String[] paymentColumns = {"Payment ID", "Patient ID", "Patient Name", "Patient Email", "Payment Status", "Date of Payment", "Treatment", "Amount Paid"};
        paymentTableModel = new DefaultTableModel(paymentColumns, 0);
        paymentTable = new JTable(paymentTableModel);
        JScrollPane paymentScrollPane = new JScrollPane(paymentTable);
        paymentScrollPane.setBorder(BorderFactory.createTitledBorder("Payments"));

        add(title, BorderLayout.NORTH);
        add(paymentScrollPane, BorderLayout.CENTER);

        loadPayments();
    }

    private void loadPayments() {
        PaymentsController controller = new PaymentsController();
        List<Payments> payments = controller.getAllPayments();
        paymentTableModel.setRowCount(0); // Clear existing rows
        for (Payments payment : payments) {
            Object[] row = {
                payment.getPaymentID(),
                payment.getPatientID(),
                payment.getPatientName(),
                payment.getPatientEmail(),
                payment.getPaymentStatus(),
                payment.getDateOfPayment(),
                payment.getTreatment(),
                payment.getAmountPaid()
            };
            paymentTableModel.addRow(row);
        }
    }
}
