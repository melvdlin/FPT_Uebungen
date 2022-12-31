package org.somevand.fpt.teaching.hilfsblaetter.immutability;

public class Date {
    private int year, day;
    private Month month;

    public Date(int year, int day, Month month) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    // copy-constructor
    public Date(Date other) {
        this.year = other.getYear();
        this.day = other.getDay();
        this.month = other.getMonth();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
