package view;

import controller.PaymentController;
import model.Payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class PaymentMethodPanel extends JPanel {

    private JTable billingTable;
    private JButton makePaymentButton;
    private JTextField amountField;
    private JLabel patientIdLabel; // Label to display patient ID
    private int patientId;

    public PaymentMethodPanel(int patientId) {
        this.patientId = patientId;
        setLayout(new BorderLayout(10, 10));

        // Patient ID Panel
        JPanel patientIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        patientIdLabel = new JLabel("Patient ID: " + patientId);
        patientIdPanel.add(patientIdLabel);

        // Billing List
        String[] columnNames = {"Treatment Fee/Consultation Fee", "Quantity", "Price"};
        Object[][] data = {
            {"Consultation fee", "", ""}
        };
        billingTable = new JTable(data, columnNames);
        JScrollPane billingScrollPane = new JScrollPane(billingTable);

        JPanel billingPanel = new JPanel(new BorderLayout());
        billingPanel.setBorder(BorderFactory.createTitledBorder("Billing List"));
        billingPanel.add(billingScrollPane, BorderLayout.CENTER);

        // Payment Methods
        JPanel paymentMethodsPanel = new JPanel(new GridLayout(4, 1));
        paymentMethodsPanel.setBorder(BorderFactory.createTitledBorder("Payment Methods"));

        JRadioButton creditButton = new JRadioButton("Credit card");

        ButtonGroup group = new ButtonGroup();
        group.add(creditButton);

        paymentMethodsPanel.add(creditButton);

        // Amount
        JPanel amountPanel = new JPanel(new BorderLayout());
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);
        amountPanel.add(amountLabel, BorderLayout.WEST);
        amountPanel.add(amountField, BorderLayout.CENTER);

        paymentMethodsPanel.add(amountPanel);

        // Make Payment Button
        makePaymentButton = new JButton("MAKE PAYMENT");

        // Add Panels to Layout
        add(patientIdPanel, BorderLayout.NORTH); // Add patient ID at the top
        add(billingPanel, BorderLayout.CENTER);
        add(paymentMethodsPanel, BorderLayout.CENTER);
        add(makePaymentButton, BorderLayout.SOUTH);

        // Make Payment Button Action
        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = amountField.getText().trim();
                String paymentMethod = getSelectedButtonText(group);
                if (!amount.isEmpty() && paymentMethod != null) {
                    double amountValue;
                    try {
                        amountValue = Double.parseDouble(amount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                        return;
                    }

                    // Open the PaymentForm with the specified amount
                    new PaymentForm(amountValue, patientId, paymentMethod);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the amount and select a payment method.");
                }
            }
        });
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
