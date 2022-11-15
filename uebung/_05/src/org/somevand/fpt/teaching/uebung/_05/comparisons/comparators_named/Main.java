package org.somevand.fpt.teaching.uebung._05.comparisons.comparators_named;

import org.somevand.fpt.teaching.uebung._05.comparisons.param_check_requirenonnull.Person;

import java.util.ArrayList;
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

        persons.forEach(System.out::println);
        System.out.println();

        persons.sort(new PersonAgeComparator());
        persons.forEach(System.out::println);
        System.out.println();

        persons.sort(new PersonNameComparator());
        persons.forEach(System.out::println);
        System.out.println();
    }
}
