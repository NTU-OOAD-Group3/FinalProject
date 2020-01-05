package org.hotelsystem.model;

public class UserInfo {
    private int userID;
    private int sex;
    private String phoneNumber;
    private String address;
    private String cardOwner;
    private String cardAccount;
    private String cardValidTime;

    public UserInfo(int userID, int sex, String phoneNumber, String address,
                    String cardOwner, String cardAccount, String cardValidTime) {
        this.userID = userID;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cardOwner = cardOwner;
        this.cardAccount = cardAccount;
        this.cardValidTime = cardValidTime;
    }

    public int getUserID() { return this.userID; }
    public int getSex() { return this.sex; }
    public String getPhoneNumber() { return this.phoneNumber; }
    public String getAddress() { return this.address; }
    public String getCardOwner() { return this.cardOwner; }
    public String getCardAccount() { return this.cardAccount; }
    public String getCardValidTime() { return this.cardValidTime; }

    public String toString() {
        String s = "UserID: " + String.valueOf(this.userID) + ",\n" +
            "Sex: " + String.valueOf(this.sex) + ",\n" +
            "PhoneNumber: " + String.valueOf(this.phoneNumber) + ",\n" +
            "Address: " + String.valueOf(this.address) + ",\n" +
            "CardOwner: " + String.valueOf(this.cardOwner) + ",\n" +
            "CardAccount: " + String.valueOf(this.cardAccount) + ",\n" +
            "CardValidTime: " + String.valueOf(this.cardValidTime) + ",\n";
        return s;
    }
}