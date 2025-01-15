package model;

import java.sql.Date;
import java.sql.Time;

public class FollowUpAppointment {
    private int followUpID;
    private int appointmentID;
    private Date followUpDate;
    private Time followUpTime;
    private String surgeonNotes;

    public FollowUpAppointment(int followUpID, int appointmentID, Date followUpDate, Time followUpTime, String surgeonNotes) {
        this.followUpID = followUpID;
        this.appointmentID = appointmentID;
        this.followUpDate = followUpDate;
        this.followUpTime = followUpTime;
        this.surgeonNotes = surgeonNotes;
    }

    public int getFollowUpID() {
        return followUpID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    public Time getFollowUpTime() {
        return followUpTime;
    }

    public String getSurgeonNotes() {
        return surgeonNotes;
    }

    public void setFollowUpID(int followUpID) {
        this.followUpID = followUpID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public void setFollowUpTime(Time followUpTime) {
        this.followUpTime = followUpTime;
    }

    public void setSurgeonNotes(String surgeonNotes) {
        this.surgeonNotes = surgeonNotes;
    }
}
