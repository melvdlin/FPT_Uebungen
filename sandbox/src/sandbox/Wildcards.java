package sandbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wildcards {

    public static void main(String[] args) {
        List<String> source = List.of("asdf", "ghij", "xyz");
        List<Object> destination = new ArrayList<>();

        load(source, destination);

        System.out.println(source);
        System.out.println(destination);
    }

    private static <T> void load(Collection<? extends T> source, Collection<? super T> destination) {
        for (var element : source) {
            destination.add(element);
        }
    }
}
