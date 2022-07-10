package org.somevand.fpt.teaching.uebung._1;

public class FizzBuzz {
    public static void fizz(int i) {
        if (i % 3 == 0) System.out.print("Fizz");
    }

    public static void buzz(int i) {
        if (i % 5 == 0) System.out.print("Buzz");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + ": ");
            fizz(i);
            buzz(i);
            System.out.println();
        }
    }
}
