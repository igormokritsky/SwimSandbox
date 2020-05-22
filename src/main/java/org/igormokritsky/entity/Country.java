package org.igormokritsky.entity;

public class Country extends Entity{

    private String countryName;

    public Country() {

    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
