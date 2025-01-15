package model;

public class Surgeon {
    private int surgeonID; // Updated field name
    private int userID; // Added field to link to User table
    private String username;
    private String email;
    private String phone;
    private String department;

    public Surgeon(int surgeonID, int userID, String username, String email, String phone, String department) {
        this.surgeonID = surgeonID;
        this.userID = userID; // Added parameter in constructor
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    // Getters and Setters
    public int getSurgeonID() {
        return surgeonID;
    }

    public void setSurgeonID(int surgeonID) {
        this.surgeonID = surgeonID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
