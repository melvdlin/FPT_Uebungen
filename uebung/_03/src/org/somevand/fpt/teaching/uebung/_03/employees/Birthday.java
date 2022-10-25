package org.somevand.fpt.teaching.uebung._03.employees;

public class Birthday {

    private final int year;
    private final Month month;
    private final int day;

    private Birthday(int year, Month month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Birthday at(int year, Month month, int day) {
        if (isValidDate(year, month, day))
            return new Birthday(year, month, day);
        else
            throw new IllegalArgumentException();
    }

    private static boolean isValidDate(int year, Month month, int day) {
        return 1 <= day && day <= month.getNumberOfDays() && 0 <= year;
    }

    public static void main(String[] ich) {
        Birthday bd = Birthday.at(2003, Month.MARCH, 3);
        System.out.println(bd);
    }

    @Override
    public String toString() {
        return year + "-" + (month.ordinal() + 1) + "-" + day;
    }
}
