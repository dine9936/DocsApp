package com.example.docsapp.Models;

public class RapidxMo {
    private String name;
    private String number;
    private String image;

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

    public RapidxMo() {
    }

    public RapidxMo(String name, String number,String image) {
        this.name = name;
        this.number = number;
        this.image = image;
    }
}
