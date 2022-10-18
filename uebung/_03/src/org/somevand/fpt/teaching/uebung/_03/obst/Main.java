package org.somevand.fpt.teaching.uebung._03.obst;

public class Main {

    public static void main(String[] args) {
        Watermelon melon1 = new Watermelon(12345678, 4.2, Color.GREEN, "Duisburg", 0.99F);
        Watermelon melon2 = new Watermelon(12345678, 4.2, Color.GREEN, "Duisburg", 0.99F);
        System.out.println("melon1.equals(melon2)   == " + melon1.equals(melon2));    // true
        System.out.println("melon2.equals(melon1)   == " + melon2.equals(melon1));    // true
        System.out.println("(melon1 == melon2)      == " + (melon1 == melon2));          // false
    }
}
