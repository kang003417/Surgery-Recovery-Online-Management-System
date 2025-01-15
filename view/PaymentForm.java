package view;

import controller.PaymentController;
import model.Payment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class PaymentForm extends JFrame {

    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JTextField amountField;
    private double amount;
    private int patientId;
    private String paymentMethod;

    public PaymentForm(double amount, int patientId, String paymentMethod) {
        this.amount = amount;
        this.patientId = patientId;
        this.paymentMethod = paymentMethod;
        setTitle("Card Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Card Number
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(20);
        formPanel.add(cardNumberLabel);
        formPanel.add(cardNumberField);

        // Expiry Date
        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        expiryDateField = new JTextField(20);
        formPanel.add(expiryDateLabel);
        formPanel.add(expiryDateField);

        // CVV
        JLabel cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField(20);
        formPanel.add(cvvLabel);
        formPanel.add(cvvField);

        // Amount
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);
        amountField.setText(String.valueOf(amount)); // Set the amount from the payment method panel
        amountField.setEditable(false);
        formPanel.add(amountLabel);
        formPanel.add(amountField);

        // Buttons
        JButton payButton = new JButton("Pay");
        JButton cancelButton = new JButton("Cancel");

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(payButton);
        buttonPanel.add(cancelButton);

        // Add Panels to Frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Pay Button Action
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText().trim();
                String expiryDate = expiryDateField.getText().trim();
                String cvv = cvvField.getText().trim();
                String amount = amountField.getText().trim();

                if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                } else {
                    // Process payment (for demo purposes, we'll just validate the card number format)
                    if (cardNumber.matches("\\d{16}")) {
                        PaymentController controller = new PaymentController();
                        Payment payment = new Payment(patientId, Double.parseDouble(amount), Date.valueOf(LocalDate.now()), paymentMethod, "Receipt123");
                        boolean paymentSuccess = controller.processPayment(payment);

                        if (paymentSuccess) {
                            JOptionPane.showMessageDialog(null, "Payment of $" + amount + " made successfully!");
                            dispose(); // Close the payment form
                        } else {
                            JOptionPane.showMessageDialog(null, "Payment failed. Please try again.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid card number. Payment failed.");
                    }
                }
            }
        });

        // Cancel Button Action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Payment cancelled.");
                dispose(); // Close the payment form
            }
        });

        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
}
