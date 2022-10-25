package org.somevand.fpt.teaching.uebung._03.employees;

public class Address {

    private final int zipcode;
    private final String city;
    private final String street;
    private final int houseNumber;

    private Address(int zipcode, String city, String street, int houseNumber) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Address at(int zipcode, String city, String street, int houseNumber) {
        return new Address(zipcode, city, street, houseNumber);
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String toString() {
        return zipcode + " " + city + "\n" + street + " " + houseNumber;
    }
}
