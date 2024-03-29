package sandbox.concurrency;

public class Lazy {
    private static Lazy instance;

    private Lazy() {

    }

    public Lazy getInstance() {
        if (instance == null) {
            synchronized (Lazy.class) {
                if (instance == null) {
                    instance = new Lazy();
                }
            }
        }
        return instance;
    }
}
