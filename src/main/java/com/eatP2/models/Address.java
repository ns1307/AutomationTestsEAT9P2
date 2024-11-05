package com.eatP2.models;

public class Address{
    private String city;
    private String district;
    private String postalCode;
    private String description;

    public Address(String city, String district, String postalCode,String description) {
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
        this.description = description;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
