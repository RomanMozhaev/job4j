package ru.job4j.firsthttp;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ValidateServiceTest {

    @Test
    public void whenUpdateThenUpdated() {
        Validate validate = ValidateService.getInstance();
        User user1 = new User(-1, "Roman", null, -1);
        validate.add(user1);
        User user2 = new User(1, "Sergey", "roman@mail", -1);
        validate.update(user2);
        String result = validate.findAll();
        result = result.substring(0, 20);
        StringBuilder expect = new StringBuilder();
        expect.append("1");
        expect.append(System.lineSeparator());
        expect.append("Sergey");
        expect.append(System.lineSeparator());
        expect.append("roman@mail");
        expect.append(System.lineSeparator());
        assertThat(result, is(expect.toString()));

    }

}