package org.somevand.fpt.teaching.uebung.old._03.taschenrechner;
import java.util.*;

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
                    for (var value : values) System.out.println(value);
                }
                case "sum" -> {
                    Double sum = 0.0;
                    for (var value : values) sum += value;
                    values.clear();
                    System.out.println(sum);
                }
                case "product" -> {
                    Double product = 1.0;
                    for (var value : values) product *= value;
                    values.clear();
                    System.out.println(product);
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
}
