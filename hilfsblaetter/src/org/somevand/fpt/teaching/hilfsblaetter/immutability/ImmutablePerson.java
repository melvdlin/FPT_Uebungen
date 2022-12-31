package org.somevand.fpt.teaching.hilfsblaetter.immutability;

import java.util.ArrayList;
import java.util.List;

// class is final to prevent extension
public final class ImmutablePerson {

    // all fields are private and final
    private final String firstName, lastName;
    private final Date dateOfBirth;
    private final Address primaryAddress;
    private final List<Address> secondaryAddresses;

    public ImmutablePerson(
            String firstName,
            String lastName,
            Date dateOfBirth,
            Address primaryAddress,
            List<Address> secondaryAddresses
    ) {
        // no need to create a defensive copy because Strings are immutable
        this.firstName = firstName;
        this.lastName = lastName;

        // defensive copy is shallow because Date and Address are both mutable but contain only Fields of immutable types
        this.dateOfBirth = new Date(dateOfBirth);
        this.primaryAddress = new Address(primaryAddress);

        // defensive copy is deep because Lists are mutable and the contained Addresses are also mutable
        this.secondaryAddresses = new ArrayList<>(secondaryAddresses.size());
        for (Address address : secondaryAddresses) {
            this.secondaryAddresses.add(new Address(address));
        }
    }

    // no need to create a defensive copy because Strings are immutable
    public String getFirstName() {
        return firstName;
    }

    // no need to create a defensive copy because Strings are immutable
    public String getLastName() {
        return lastName;
    }

    // create a defensive copy so the caller cannot mutate the date of birth of this person
    public Date getDateOfBirth() {
        return new Date(dateOfBirth);
    }

    // create a defensive copy so the caller cannot mutate the primary address of this person
    public Address getPrimaryAddress() {
        return new Address(primaryAddress);
    }

    // create a defensive copy so the caller cannot mutate the list of secondary addresses of this person
    // or the addresses contained therein
    public List<Address> getSecondaryAddresses() {
        List<Address> result = new ArrayList<>(secondaryAddresses.size());
        for (Address address : secondaryAddresses) {
            result.add(new Address(address));
        }
        return result;
    }
}
