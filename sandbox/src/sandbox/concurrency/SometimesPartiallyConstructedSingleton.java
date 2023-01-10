package sandbox.concurrency;

public class SometimesPartiallyConstructedSingleton extends SomeClass {
    private static SometimesPartiallyConstructedSingleton instance;
    private int anInt;
    private int anotherInt;

    public SometimesPartiallyConstructedSingleton(int anInt, int anotherInt) {
        super("abc", "def");
        this.anInt = anInt;
        this.anotherInt = anotherInt;
    }

    public static SometimesPartiallyConstructedSingleton getInstance() {
        if (instance == null) {
            instance = new SometimesPartiallyConstructedSingleton(1, 2);
        }
        return instance;
    }

    public int getAnInt() {
        return anInt;
    }

    public int getAnotherInt() {
        return anotherInt;
    }
}
