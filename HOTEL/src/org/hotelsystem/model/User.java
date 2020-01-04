package org.hotelsystem.model;

public class User {
    private int userID;
    private int userType; // host & normal user
    private String username;
    private String password;

    public User(int userID, int userType, String username, String password){
        this.userID = userID;
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public int getUserID() { return this.userID; }
    public int userType() { return this.userType; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }

    public String toString() {
        String s = "userID: " + String.valueOf(this.userID) + ", " +
            "userType: " + String.valueOf(this.userType) + ", " +
            "username: " + this.username + ", " +
            "password: " + this.password;
        return s;
    }
}
