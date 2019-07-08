package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenThen() {
        User user1 = new User("First User", 2, new GregorianCalendar(1987, 1, 1));
        User user2 = new User("First User", 2, new GregorianCalendar(1987, 1, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, null);
        map.put(user2, null);
        System.out.println(map);
    }

}