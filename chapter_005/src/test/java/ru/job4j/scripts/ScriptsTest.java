package ru.job4j.scripts;

import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ScriptsTest {

    @Test
    public void whenMapThenListOfScripts() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, List.of(2, 3));
        map.put(2, List.of(3, 4));
        map.put(3, List.of(4, 5));
        map.put(4, List.of(7));
        map.put(5, List.of(7, 8));
        map.put(9, List.of(7, 1));
        Scripts scripts = new Scripts();
        List<Integer> list = scripts.load(map, 1);
        assertThat(list.stream().sorted().collect(Collectors.toList()),
                is(List.of(2, 3, 4, 5, 7, 8)));
    }

}