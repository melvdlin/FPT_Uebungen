package org.somevand.fpt.teaching.uebung._05.comparisons.comparators_named;

import java.util.Comparator;

class PersonAgeComparator implements Comparator<Person> {

    public PersonAgeComparator() {
    }

    @Override
    public int compare(Person p1, Person p2) {
        if (p1 == p2) return 0;
        if (p1 == null) return -1;
        if (p2 == null) return 1;
        return p1.getAge() < p2.getAge() ? -1 : p1.getAge() == p2.getAge() ? 0 : 1;
    }
}
