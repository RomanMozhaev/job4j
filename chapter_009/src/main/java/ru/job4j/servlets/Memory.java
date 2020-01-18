package ru.job4j.servlets;

import ru.job4j.firsthttp.MemoryStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Memory {

    /**
     * the instance for singleton.
     */
    private static final Memory INSTANCE = new Memory();
    /**
     * the map with users.
     */
    private AtomicInteger serialID = new AtomicInteger();

    Map<Integer, JsonUser> map = new ConcurrentHashMap<>();

    public static Memory getInstance() {
        return INSTANCE;
    }

    public boolean add(JsonUser user) {
        int id = this.serialID.incrementAndGet();
        return this.map.putIfAbsent(id, user) == null;
    }
}
