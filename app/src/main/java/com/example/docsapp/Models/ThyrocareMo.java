package com.example.docsapp.Models;

public class ThyrocareMo {
    private String name;
    private String number;

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

    public ThyrocareMo(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
