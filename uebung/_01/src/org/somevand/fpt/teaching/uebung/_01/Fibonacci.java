package org.somevand.fpt.teaching.uebung._01;

public class Fibonacci {
    public static long fibLinear(long index) {
        if (index == 0) return 0;
        long retVal = 1, prev = 0, temp;
        for (long i = 1; i < index; i++) {
            temp = retVal;
            retVal += prev;
            prev = temp;
        }

        return retVal;
    }

    public static long fibRecursive(long index) {
        if (index < 1) return 0;
        if (index == 1) return 1;
        return fibRecursive(index - 1) + fibRecursive(index - 2);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 42; i++) {
            System.out.println("fibLinear(" + i + ") = " + fibLinear(i));
            System.out.println("fibRecursive(" + i + ") = " + fibRecursive(i));
        }
    }
}
