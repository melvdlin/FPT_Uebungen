package sandbox.singleton;

public class SingletonUser {
    public static void main(String[] args) {
        var lazySingleton = LazySingleton.getInstance();
        var anotherLazySingleton = LazySingleton.getInstance();
        var synchronizedLazySingleton = SynchronizedLazySingleton.getInstance();
        var anotherSynchronizedLazySingleton = SynchronizedLazySingleton.getInstance();

        System.out.println(lazySingleton);
        System.out.println(anotherLazySingleton);
        System.out.println(synchronizedLazySingleton);
        System.out.println(anotherSynchronizedLazySingleton);
        System.out.println(lazySingleton == anotherLazySingleton);
        System.out.println(synchronizedLazySingleton == anotherSynchronizedLazySingleton);
    }
}
