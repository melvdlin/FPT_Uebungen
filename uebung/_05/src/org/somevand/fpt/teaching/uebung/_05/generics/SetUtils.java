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
}
