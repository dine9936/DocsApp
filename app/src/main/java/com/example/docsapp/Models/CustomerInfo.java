package com.example.docsapp.Models;

public class CustomerInfo {
    private String userName;
    private String userPhone;
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public CustomerInfo() {
    }

    public CustomerInfo(String userName, String userPhone, String userEmail) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }
}
