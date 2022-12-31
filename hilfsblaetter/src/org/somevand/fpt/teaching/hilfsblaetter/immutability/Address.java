package org.somevand.fpt.teaching.hilfsblaetter.immutability;

public class Address {
    private String country, city, street;
    private int zipCode, houseNumber;

    public Address(String country, int zipCode, String city, String street, int houseNumber) {
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    // copy-constructor
    public Address(Address other) {
        this.country = other.getCountry();
        this.zipCode = other.getZipCode();
        this.city = other.getCity();
        this.street = other.getStreet();
        this.houseNumber = other.getHouseNumber();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
