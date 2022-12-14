package org.somevand.fpt.teaching.hilfsblaetter.enums;

import java.util.Iterator;
import java.util.List;

// enums can implement interfaces like any other class
public enum Month implements Iterable<Integer> {
    // declaration of enum constants
    // explicit call of Enum(int day) constructor
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    // explicit call of parameterless constructor
    APRIL(),
    MAY(31),
    // implicit call of parameterless constructor
    JUNE,
    JULY(31),
    AUGUST(31),
    SEPTEMBER,
    OCTOBER(31),
    NOVEMBER,
    DECEMBER(31);
    // declaration of enum constants terminated by a semicolon

    // enums can have fields just like any other class
    private final int days;
    // values() returns an array of all instances of this enum class;
    // List.of() builds and returns an immutable List
    private static final List<Month> months = List.of(values());

    // constructors are implicitly private
    Month() {
        this(30);
    }

    Month(int days) {
        this.days = days;
    }

    // enums can have methods just like any other class
    public static List<Month> getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }

    @Override
    public Iterator<Integer> iterator() {
        // an iterator that iterates over all the days in a given month
        return new Iterator<>() {
            int nextDay = 1;
            @Override
            public boolean hasNext() {
                return nextDay <= days;
            }

            @Override
            public Integer next() {
                return nextDay++;
            }
        };
    }
}
