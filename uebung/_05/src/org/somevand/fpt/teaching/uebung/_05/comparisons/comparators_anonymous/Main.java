package org.somevand.fpt.teaching.uebung._05.comparisons.comparators_anonymous;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(List.of(
                new Person("Mustermann", "Max",34, 1.86),
                new Person("Obamna", "",61, 1.776),
                new Person("Obamna", "Mrs", 58, 1.64),
                new Person("Bloch", "Josh", 61, 1.23),
                new Person("Doe", "Jane", 23, 1.71),
                new Person("Papula", "Lothar", 79, 1.91)
        ));

        Comparator<Person> firstNameComparator = new Comparator<>() {
            @Override
            public int compare(Person p1, Person p2) {
                if (p1 == p2) return 0;
                if (p1 == null) return -1;
                if (p2 == null) return 1;
                return p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
            }
        };

        persons.forEach(System.out::println);
        System.out.println();

        persons.sort(firstNameComparator);
        persons.forEach(System.out::println);
        System.out.println();
    }
}
