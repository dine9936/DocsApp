package com.example.docsapp.Models;

public class CustomerInfo {
    private String userName;
    private String userPhone;
    private String userEmail;
    private String age;
    private String address;
    private String pincode;
    private String packagename;
    private String discount;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CustomerInfo(String gender, String userName, String userPhone, String userEmail, String age, String address, String pincode, String packagename, String discount) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.age = age;
        this.address = address;
        this.pincode = pincode;
        this.packagename = packagename;
        this.discount = discount;
        this.gender = gender;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public CustomerInfo() {
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


}
