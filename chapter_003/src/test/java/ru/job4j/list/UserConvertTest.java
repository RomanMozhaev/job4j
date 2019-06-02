package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    /**
     * the test checks that HashMap has the user with key 11.
     */
    @Test
    public void when3UsersThenAddedToHashMap() {
        List<User> list = new ArrayList<>(Arrays.asList(
                new User(3, "Peter", "NYC"),
                new User(5, "Matt", "Washington"),
                new User(7, "Craig", "Las Vegas"),
                new User(9, "Sarah", "Kent"),
                new User(11, "Amber", "NYC")
        ));
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result = userConvert.process(list);
        assertThat(result.get(11).getName(), is("Amber"));
    }
}