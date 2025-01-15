package model;

public class Admin {
    private int adminID; // Updated field name
    private int userID; // Added field to link to User table
    private String username;
    private String email;
    private String phone;

    public Admin(int adminID, int userID, String username, String email, String phone) {
        this.adminID = adminID;
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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
}
