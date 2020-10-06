package com.example.docsapp.Models;

public class ThyrocareMo {
    private String name;
    private String number;
    private String image;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ThyrocareMo() {
    }

    public ThyrocareMo(String name, String number,String image,String price) {
        this.name = name;
        this.number = number;
        this.image = image;
        this.price = price;
    }
}
