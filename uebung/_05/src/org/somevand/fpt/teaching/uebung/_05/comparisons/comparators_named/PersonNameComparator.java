package org.somevand.fpt.teaching.uebung._05.comparisons.comparators_named;

import java.util.Comparator;

class PersonNameComparator implements Comparator<Person> {

    public PersonNameComparator() {
    }

    @Override
    public int compare(Person p1, Person p2) {
        if (p1 == p2) return 0;
        if (p1 == null) return -1;
        if (p2 == null) return 1;
        if (p1.getLastName().compareToIgnoreCase(p2.getLastName()) == 0) {
            return 0;
        }
        return p1.getLastName().compareToIgnoreCase(p2.getLastName());
    }
}
