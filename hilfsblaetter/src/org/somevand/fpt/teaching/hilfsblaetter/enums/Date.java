package org.somevand.fpt.teaching.hilfsblaetter.enums;

public final class Date {
    private final int year, day;
    private final Month month;

    public Date(int year, int day, Month month) {
        if (year < 0) throw new IllegalArgumentException("year must not be negative");
        if (day < 1) throw new IllegalArgumentException("day must not be less than 1");
        // instance methods of an enum can be called just like those of other classes
        if (day > month.getDays())
            throw new IllegalArgumentException("day must not be greater than number of days in month");
        this.year = year;
        this.day = day;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }
}
