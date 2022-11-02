package org.somevand.fpt.teaching.uebung._03.employees;

public class EmployeeID {

    private static int employeeCount = 0;

    private String firstName;
    private String lastName;
    private Address address;
    private final Birthday dateOfBirth;;
    private SecurityAccessLevel accessLevel;
    private final int UID;

    public EmployeeID(String firstName, String lastName, Address address, Birthday dateOfBirth, SecurityAccessLevel accessLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.accessLevel = accessLevel;
        this.UID = employeeCount++;
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

    public Birthday getDateOfBirth() {
        return dateOfBirth;
    }

    public SecurityAccessLevel getAccessLevel() {
        return accessLevel;
    }

    public int getUID() {
        return UID;
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

    public void setAccessLevel(SecurityAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
