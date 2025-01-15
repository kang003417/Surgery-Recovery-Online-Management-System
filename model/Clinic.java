package model;

public class Clinic {
    private int clinicID;
    private String clinicName;
    private String clinicAddress;
    private String clinicPhone;
    private String clinicPostcode;
    private String patientPostcode;
    
    public Clinic(int clinicID, String clinicName, String clinicAddress, String clinicPhone, String clinicPostcode, String patientPostcode) {
        this.clinicID = clinicID;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicPhone = clinicPhone;
        this.clinicPostcode = clinicPostcode;
        this.patientPostcode = patientPostcode;
    }

    public int getClinicID() {
        return clinicID;
    }

    public String getClinicname() {
        return clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }
    
    public String getClinicpostcode() {
        return clinicPostcode;
    }
    
    public String getPatientpostcode() {
        return patientPostcode;
    }
}
