package org.somevand.fpt.teaching.uebung._03.employees;

import java.time.LocalDateTime;

public class EmployeeID {

    private static int employeeCount = 0;

    private String firstName;
    private String lastName;
    private Address address;
    private final LocalDateTime dateOfBirth;;
    private final int uid;

    EmployeeID(String firstName, String lastName, Address address, LocalDateTime dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.uid = employeeCount++;
    }

    public EmployeeID create(String firstName, String lastName, Address address, LocalDateTime dateOfBirth) {
        return new EmployeeID(firstName, lastName, address, dateOfBirth);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public int getUid() {
        return uid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
