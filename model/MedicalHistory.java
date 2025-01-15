package model;

import java.util.Date;

public class MedicalHistory {
    private int historyID;
    private int patientID;
    private int surgeonID;
    private int appointmentID;
    private String conditions;
    private Date diagnosisDate;
    private String treatment;
    private Date treatmentDate;

    public MedicalHistory(int historyID, int patientID, int surgeonID, int appointmentID, String conditions, Date diagnosisDate, String treatment, Date treatmentDate) {
        this.historyID = historyID;
        this.patientID = patientID;
        this.surgeonID = surgeonID;
        this.appointmentID = appointmentID;
        this.conditions = conditions;
        this.diagnosisDate = diagnosisDate;
        this.treatment = treatment;
        this.treatmentDate = treatmentDate;
    }

    public int getHistoryID() {
        return historyID;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getSurgeonID() {
        return surgeonID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getConditions() {
        return conditions;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setSurgeonID(int surgeonID) {
        this.surgeonID = surgeonID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setTreatmentDate(Date treatmentDate) {
        this.treatmentDate = treatmentDate;
    }
}
