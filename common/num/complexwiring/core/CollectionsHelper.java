package num.complexwiring.core;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class CollectionsHelper {
    public static <T> Set<T> createWeakHashSet() {
        Set<T> weakHashSet = Collections.newSetFromMap(new WeakHashMap<T, Boolean>());
        return weakHashSet;
    }
}
