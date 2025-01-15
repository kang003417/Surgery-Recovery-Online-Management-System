package model;

import java.util.Date;

public class Treatment {
    private int treatmentID;
    private int appointmentID;
    private int surgeonID;
    private String treatmentDetails;
    private Date treatmentDate;

    public Treatment(int treatmentID, int appointmentID, int surgeonID, String treatmentDetails, Date treatmentDate) {
        this.treatmentID = treatmentID;
        this.appointmentID = appointmentID;
        this.surgeonID = surgeonID;
        this.treatmentDetails = treatmentDetails;
        this.treatmentDate = treatmentDate;
    }

    public int getTreatmentID() {
        return treatmentID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getSurgeonID() {
        return surgeonID;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setSurgeonID(int surgeonID) {
        this.surgeonID = surgeonID;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public void setTreatmentDate(Date treatmentDate) {
        this.treatmentDate = treatmentDate;
    }
}
