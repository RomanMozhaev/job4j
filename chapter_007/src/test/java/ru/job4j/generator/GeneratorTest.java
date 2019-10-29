package ru.job4j.generator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {
    /**
     * the generator test when all keys in the text are unique.
     * @throws NoKeyException
     * @throws MapNotEmptyException
     */
    @Test
    public void whenTwoKeysThenResult() throws NoKeyException, MapNotEmptyException {
        String string = "I am ${name}, Who are ${subject}";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Oleg");
        keys.put("subject", "you");
        Generator g = new Generator();
        String result = g.generate(string, keys);
        String expect = "I am Oleg, Who are you";
        assertEquals(result, expect);
    }

    /**
     * the generator text when all keys in the text are equal.
     * @throws NoKeyException
     * @throws MapNotEmptyException
     */
    @Test
    public void whenThreeKeysThenHaaHaa() throws NoKeyException, MapNotEmptyException {
        String string = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = new HashMap<>();
        keys.put("sos", "Haa");
        Generator g = new Generator();
        String result = g.generate(string, keys);
        String expect = "Help, Haa, Haa, Haa";
        assertEquals(result, expect);
    }
}