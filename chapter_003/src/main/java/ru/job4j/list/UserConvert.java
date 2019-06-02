package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**
 * the UserConvert converts List to HashMap.
 */

public class UserConvert {
    /**
     * The process converts List to HashMap
     * @param list - the list for conversion
     * @return - the HashMap. The key is User ID.
     */
    HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hashMap = new HashMap<>();
        for (User user : list) {
            hashMap.put(user.getId(), user);
        }
        return hashMap;
    }
}
