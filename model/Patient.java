package model;

public class Patient {
    private int userId;
    private String name;
    private String phone;
    private String email;
    private String birthday;
    private String bloodGroup;
    private String emergencyContact;
    private String address;
    private String postcode;

    public Patient(int userId, String name, String phone, String email, String birthday, String bloodGroup, String emergencyContact, String address, String postcode) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.bloodGroup = bloodGroup;
        this.emergencyContact = emergencyContact;
        this.address = address;
        this.postcode = postcode;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
