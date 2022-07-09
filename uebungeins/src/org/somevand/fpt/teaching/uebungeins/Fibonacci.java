package org.somevand.fpt.teaching.uebungeins;

public class Fibonacci {
    public static long fibLinear(long index) {
        long retVal = 1, prev = 1, temp;
        for (long i = 1; i < index; i++) {
            temp = retVal;
            retVal += prev;
            prev = temp;
        }

        return retVal;
    }

    public static long fibRecursive(long index) {
        if (index <= 1) return 1;
        return fibRecursive(index - 1) + fibRecursive(index - 2);
    }

    public static void main(String[] args) {
        long index1 = 54;
        long index2 = 48;
        System.out.println("fibLinear(" + index1 + ") = " + fibLinear(index1));
        System.out.println("fibLinear(" + index2 + ") = " + fibLinear(index2));
        System.out.println("fibRecursive(" + index2 + ") = " + fibRecursive(index2));
    }
}
