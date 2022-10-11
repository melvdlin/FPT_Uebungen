package org.somevand.fpt.teaching.uebung._01;

public class FizzBuzz {
    static String fizzbuzz(int i) {
        String out = "";

        if (i % 3 == 0)
            out += "Fizz";
        if (i % 5 == 0)
            out += "Buzz";

        return out;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + ": ");
            System.out.println(fizzbuzz(i));
        }
    }
}
