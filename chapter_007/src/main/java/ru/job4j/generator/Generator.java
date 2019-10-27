package ru.job4j.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the class of Generator replaces the keys in the input text to keys from the map.
 */
public class Generator {
    /**
     * KEYS is a pattern for searching keys which looks like ${...}.
     */
    private static final Pattern KEYS = Pattern.compile("\\$\\{([\\w\\.]+)\\}");

    /**
     * the method for replacing keys places in the text to words from the map.
     * @param template - the text which has keys as KEYS.
     * @param map - the map of values for replacement.
     * @return - the text with inserted words from the map.
     * @throws NoKeyException - exception if the map does not contain required key.
     * @throws MapNotEmptyException - exception if the map has more keys than it is necessary.
     */
    public String generate(String template, Map<String, String> map) throws NoKeyException, MapNotEmptyException {
        Map<String, String> checkMap = new HashMap<>(map);
        StringBuffer result = new StringBuffer();
        Matcher m = KEYS.matcher(template);
        while (m.find()) {
            String replacement = getValue(map, m.group(1), checkMap);
            m.appendReplacement(result, replacement);
        }
        checkMapEmpty(checkMap);
        m.appendTail(result);
        return result.toString();
    }

    /**
     * the method returns the value which is matched to the key.
     * @param map - the map with keys for replacement
     * @param key - the key for replacement
     * @param checkMap - the map for control of the using keys. If key is used ones, it is removed from the checkMap.
     * @return - the value for replacement.
     * @throws NoKeyException - exception if the key does not contain in the map.
     */
    private String getValue(Map<String, String> map, String key, Map<String, String> checkMap) throws NoKeyException {
        String replacement = map.get(key);
        checkMap.remove(key);
        if (replacement == null) {
            throw new NoKeyException("One or more keys were not found in the map.");
        }
        return replacement;
    }

    /**
     * the method checks whether all keys from the map were used or not.
     * @param map - the checkMap
     * @throws MapNotEmptyException exception if one or more keys were not used.
     */
    private void checkMapEmpty(Map<String, String> map) throws MapNotEmptyException {
        if (!map.isEmpty()) {
            throw new MapNotEmptyException("Map has more keys than the template has.");
        }
    }

}
