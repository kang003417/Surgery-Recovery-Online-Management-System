package model;

import java.util.regex.Pattern;

public class User {
    private int userId; // Added userId field
    private String username;
    private String email;
    private String password;
    private String phone; // Added phone field
    private String userType;
    private String bloodGroup;
    private String birthDate;
    private String gender; // Added gender field
    private String postcode;
    
    public User(int userId, String username, String email, String password, String phone, String userType, String bloodGroup, String birthDate, String gender, String postcode) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password; 
        this.phone = phone;
        this.userType = userType;
        this.bloodGroup = bloodGroup;
        this.birthDate = birthDate;
        this.gender = gender;
        this.postcode = postcode;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }
    
    public String getPassword() {
        return password; 
    }
    
    public void setPassword(String password) {
        this.password = password; // Ensure this is hashed and salted
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getUserType() { 
        return userType; 
    }
    
    public void setUserType(String userType) {
        this.userType = userType; 
    }
    
    public String getBloodGroup() { 
        return bloodGroup; 
    }
    
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup; 
    }
    
    public String getBirthDate() { 
        return birthDate;
    }
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate; 
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getPostcode(){
        return postcode;
    }
    
    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
    
    // Method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
