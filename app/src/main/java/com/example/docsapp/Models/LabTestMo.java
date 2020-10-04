package com.example.docsapp.Models;

import java.util.SplittableRandom;

public class LabTestMo {
    private String image;
    private String packagename;
    private String notest;
    private String mrp;
    private String testprice;
    private String popular;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getNotest() {
        return notest;
    }

    public void setNotest(String notest) {
        this.notest = notest;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getTestprice() {
        return testprice;
    }

    public void setTestprice(String testprice) {
        this.testprice = testprice;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

    public LabTestMo() {
    }

    public LabTestMo(String id,String image, String packagename, String notest, String mrp, String testprice, String popular) {
        this.image = image;
        this.packagename = packagename;
        this.notest = notest;
        this.mrp = mrp;
        this.testprice = testprice;
        this.popular = popular;
        this.id = id;
    }
}
