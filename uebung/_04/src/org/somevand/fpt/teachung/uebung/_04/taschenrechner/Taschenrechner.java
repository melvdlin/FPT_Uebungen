package org.somevand.fpt.teachung.uebung._04.taschenrechner;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Taschenrechner {
    private final static String helpText = "enter a number, \"show\", \"sum\", \"product\" or \"quit\"";
    private final List<Double> values = new LinkedList<>();
    private final Scanner in = new Scanner(System.in);

    public boolean advance() {
        String token = in.next();
        try {
            values.add(Double.parseDouble(token));
        } catch (NumberFormatException e) {
            switch (token.toLowerCase()) {
                case "show" -> {
                    show();
                }
                case "sum" -> {
                    System.out.println(sumAndClear());
                }
                case "product" -> {
                    System.out.println(productAndClear());
                }
                case "quit" -> {
                    in.close();
                    return false;
                }
                default -> System.out.println(helpText);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Taschenrechner tr = new Taschenrechner();
        while(tr.advance());
    }

    private void show() {
        for (var value : values) System.out.println(value);
    }
    private double sumAndClear() {
        Double sum = 0.0;
        for (var value : values) sum += value;
        values.clear();
        return sum;
    }

    private double productAndClear() {
        Double product = 1.0;
        for (var value : values) product *= value;
        values.clear();
        return product;
    }
}
