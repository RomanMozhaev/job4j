package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
    }

    public String value(String key) {
        throw new UnsupportedOperationException("Don't impl this method yet!");
    }
}
