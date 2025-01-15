package model;

import java.sql.Date;

public class Payment {
    private int paymentID;
    private int patientID;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String receipt;

    // Constructor with paymentID
    public Payment(int paymentID, int patientID, double amount, Date paymentDate, String paymentMethod, String receipt) {
        this.paymentID = paymentID;
        this.patientID = patientID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receipt = receipt;
    }

    // Constructor without paymentID (for auto-increment scenarios)
    public Payment(int patientID, double amount, Date paymentDate, String paymentMethod, String receipt) {
        this.patientID = patientID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receipt = receipt;
    }

    // Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
