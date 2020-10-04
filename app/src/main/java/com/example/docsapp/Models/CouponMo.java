package com.example.docsapp.Models;

public class CouponMo {
    private String packeagename;
    private String discount;

    public CouponMo() {
    }

    public String getPackeagename() {
        return packeagename;
    }

    public void setPackeagename(String packeagename) {
        this.packeagename = packeagename;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public CouponMo(String packeagename, String discount) {
        this.packeagename = packeagename;
        this.discount = discount;
    }
}
