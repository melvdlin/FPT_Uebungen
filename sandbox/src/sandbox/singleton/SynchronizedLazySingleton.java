package sandbox.singleton;

public class SynchronizedLazySingleton {
    private volatile static SynchronizedLazySingleton instance;

    private SynchronizedLazySingleton() {
    }

    public static SynchronizedLazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new SynchronizedLazySingleton();
                }
            }
        }
        return instance;
    }
}
