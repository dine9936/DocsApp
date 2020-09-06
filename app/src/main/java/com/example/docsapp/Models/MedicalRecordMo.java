package com.example.docsapp.Models;

public class MedicalRecordMo {
    private String typecard;
    private String docimage;
    private String docname;
    private String docspec;
    private String dateco;
    private String datetest;
    private String packkagename;
    private String price;

    public String getTypecard() {
        return typecard;
    }

    public void setTypecard(String typecard) {
        this.typecard = typecard;
    }

    public String getDocimage() {
        return docimage;
    }

    public void setDocimage(String docimage) {
        this.docimage = docimage;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocspec() {
        return docspec;
    }

    public void setDocspec(String docspec) {
        this.docspec = docspec;
    }

    public String getDateco() {
        return dateco;
    }

    public void setDateco(String dateco) {
        this.dateco = dateco;
    }

    public String getDatetest() {
        return datetest;
    }

    public void setDatetest(String datetest) {
        this.datetest = datetest;
    }

    public String getPackkagename() {
        return packkagename;
    }

    public void setPackkagename(String packkagename) {
        this.packkagename = packkagename;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public MedicalRecordMo() {
    }

    public MedicalRecordMo(String typecard,String  docimage, String docname, String docspec, String dateco, String datetest, String packkagename, String price) {
        this.docimage = docimage;
        this.docname = docname;
        this.docspec = docspec;
        this.dateco = dateco;
        this.datetest = datetest;
        this.packkagename = packkagename;
        this.price = price;
        this.typecard = typecard;
    }
}
