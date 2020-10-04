package com.example.docsapp.Models;

public class PackageInfoMo {
    private String type;
    private String info;

    public PackageInfoMo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public PackageInfoMo(String type, String info) {
        this.type = type;
        this.info = info;
    }
}
