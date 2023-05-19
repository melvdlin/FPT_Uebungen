package org.somevand.fpt.teaching.uebung._05.generics;

import java.util.*;

public class SetUtils {

    @SafeVarargs
    public static <T> Set<T> union(Set<? extends T> ...sets) {
        Set<T> result = new HashSet<>();
        for (Set<? extends T> set : sets) {
            result.addAll(set);
        }
        return result;
    }

    public static <T> Set<T> intersection(Set<T> set, Set<?> ...others) {
        Set<T> result = new HashSet<>();
        for (T item : set) {
            boolean present = true;
            for (Set<?> other : others) {
                present &= other.contains(item);
            }
            if (present) {
                result.add(item);
            }
        }

        return result;
    }

    public static <T> Set<T> difference(Set<T> in, Set<?> notIn) {
        Set<T> result = new HashSet<>();
        for (T item : in) {
            if (!notIn.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> is = new HashSet<>();
        Set<Double> ds = new HashSet<>();
        Set<Number> ns = new HashSet<>();
        Collections.addAll(is, 1, 2, 3, 4, 5, 6, 7, 8);
        Collections.addAll(ds, 1.0, 2.0, 4.0, 8.0, 16.0, 32.0, 64.0, 128.0);
        Collections.addAll(ns, 1.0, 2.0, 3.0, 5.0, 8, 15, 23, 38);
        System.out.println(union(is, ds));
        System.out.println(intersection(ns, is, ds));
        System.out.println(difference(is, ds));
        System.out.println(Integer.valueOf(1).equals(Double.valueOf(1)));
    }
}
