package org.somevand.fpt.teaching.uebung._03.employees;

public final class Birthday {

    private final int year;
    private final Month month;
    private final int day;

    public Birthday(int year, Month month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException();
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private static boolean isValidDate(int year, Month month, int day) {
        return 1 <= day && day <= month.getNumberOfDays() && 0 <= year;
    }

    @Override
    public String toString() {
        // return year + "-" + (month.ordinal() + 1) + "-" + day;
        return String.format("%04d-%02d-%02d", year, month.ordinal() + 1, day);
    }

    public static void main(String[] ich) {
        var bd = new Birthday(2003, Month.MARCH, 3);
        System.out.println(bd);
    }
}
