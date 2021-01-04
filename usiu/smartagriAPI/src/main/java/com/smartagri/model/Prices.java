package com.smartagri.model;

public class Prices {

    private String nafisDate;

    public Prices(String nafisDate) {
        this.nafisDate = nafisDate;
    }

    public String getNafisDate() {
        return nafisDate;
    }

    public void setNafisDate(String nafisDate) {
        this.nafisDate = nafisDate;
    }

    @Override
    public String toString() {
        return nafisDate;
    }
}
