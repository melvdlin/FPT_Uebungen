package sandbox.concurrency;

import java.util.Comparator;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.function.Function;

public class ResourceUIDPool<T> implements Comparator<T>, Function<T, Integer> {
    private final WeakHashMap<T, Integer> resourceIDs = new WeakHashMap<>();
    private final Random resourceIDGenerator = new Random(System.currentTimeMillis());

    synchronized public int register(T o) {
        int resourceID = System.identityHashCode(o);
        while(resourceIDs.containsValue(resourceID)) {
            resourceID = resourceIDGenerator.nextInt();
        }
        resourceIDs.put(o, resourceID);
        return resourceID;
    }

    @Override
    synchronized public int compare(T o1, T o2) {
        try {
            return resourceIDs.get(o1).compareTo(resourceIDs.get(o2));
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException(npe);
        }
    }

    synchronized public int get(T o) {
        if (!resourceIDs.containsKey(o)) {
            return register(o);
        }
        return resourceIDs.get(o);
    }

    @Override
    synchronized public Integer apply(T o) {
        return get(o);
    }
}
