package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Payments {
    private int paymentID;
    private int patientID;
    private String patientName;
    private String patientEmail;
    private String paymentStatus;
    private Date dateOfPayment;
    private String treatment;
    private BigDecimal amountPaid;

    public Payments(int paymentID, int patientID, String patientName, String patientEmail, String paymentStatus, Date dateOfPayment, String treatment, BigDecimal amountPaid) {
        this.paymentID = paymentID;
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.paymentStatus = paymentStatus;
        this.dateOfPayment = dateOfPayment;
        this.treatment = treatment;
        this.amountPaid = amountPaid;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public String getTreatment() {
        return treatment;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
}
