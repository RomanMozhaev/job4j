package ru.job4j.cash;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * the class of the cash.
 * The cash is based on the soft references.
 */
public class Cash {
    /**
     * the map with cashed objects.
     */
    private Map<Object, SoftReference> cash = new HashMap<>();

    /**
     * the method returns the object from the cash if it is there or null.
     * @param key - the key of the cashed object in the map.
     * @retur  the soft reference of the object or null.
     */
    public SoftReference get(Object key) {
        SoftReference result = null;
        if (this.cash.containsKey(key)) {
            result = cash.get(key);
        }
        return result;
    }

    /**
     * The method adds object to the cash if it is not there.
     * @param key - the key for the object.
     * @param value - the object.
     * @return - the soft reference to the object in the cash.
     */
    public SoftReference add(Object key, Object value) {
        SoftReference<Object> softReference = new SoftReference<>(value);
        cash.put(key, softReference);
        return softReference;
    }

}
