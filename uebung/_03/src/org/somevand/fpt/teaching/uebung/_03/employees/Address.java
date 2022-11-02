package org.somevand.fpt.teaching.uebung._03.employees;

public final class Address {

    private final int zipcode;
    private final String city;
    private final String street;
    private final int houseNumber;

    public Address(int zipcode, String city, String street, int houseNumber) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
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
        // return zipcode + " " + city + "\n" + street + " " + houseNumber;
        return String.format("%05d %s%n%s %d", zipcode, city, street, houseNumber);
    }

    public static void main(String[] args) {
        var address = new Address(46537, "Dinslaken", "Im Kauenkorb", 49);
        System.out.println(address);
    }
}
