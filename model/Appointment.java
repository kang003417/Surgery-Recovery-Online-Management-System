package model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointmentID;
    private int patientID;
    private String patientName;
    private Date appointmentDate;
    private Time appointmentTime;
    private String surgeon;

    public Appointment(int appointmentID, int patientID, String patientName, Date appointmentDate, Time appointmentTime, String surgeon) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.surgeon = surgeon;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public String getSurgeon() {
        return surgeon;
    }
}
