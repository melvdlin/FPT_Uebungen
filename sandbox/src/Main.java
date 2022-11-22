import java.util.*;

public class Main {

    public static void main(String[] args) {
        String o;
        o = "I'm a string";

        System.out.println(null instanceof Object);

        System.out.println(o);
        o = Objects.requireNonNull(o);
        System.out.println(o);
        o = Objects.requireNonNull(null);
        System.out.println(o);

        List<SomeSubClass> l = new ArrayList<>();
        l.add(new SomeSubClass(1, 2));
        l.add(new SomeSubClass(2, 3));
        l.add(new SomeSubClass(3, 4));

        l.sort(new SomeSubComparator());
        l.sort(new SomeComparator());
    }
}

class SomeClass {
    private final int x;

    public SomeClass(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}


class SomeSubClass extends SomeClass {

    private final int y;

    public SomeSubClass(int x, int y) {
        super(x);
        this.y = y;
    }

    public int getY() {
        return y;
    }
}

class SomeComparator implements Comparator<SomeClass> {

    @Override
    public int compare(SomeClass o1, SomeClass o2) {
        return Integer.compare(o1.getX(), o2.getX());
    }
}

class SomeSubComparator implements Comparator<SomeSubClass> {

    @Override
    public int compare(SomeSubClass o1, SomeSubClass o2) {
        return Integer.compare(o1.getX() + o1.getY(), o2.getX() + o2.getY());
    }
}
