package com.example.docsapp.Models;

public class CitiesMo {
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CitiesMo() {
    }

    public CitiesMo(String cityName) {
        this.cityName = cityName;
    }
}
