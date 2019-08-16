package ru.job4j.scripts;

import java.util.*;
import java.util.stream.Collectors;

public class Scripts {

    private HashSet<Integer> scriptsList = new HashSet<>();

    public List<Integer> load(Map<Integer, List<Integer>> scriptsTable, Integer scriptLoad) {
        this.scriptsList.addAll(loadScripts(scriptsTable, scriptLoad));
        return this.scriptsList.stream().collect(Collectors.toList());
    }

    private HashSet<Integer> loadScripts(Map<Integer, List<Integer>> scriptsTable, Integer scriptLoad) {
        HashSet<Integer> set = new HashSet<>();
        if (scriptsTable != null) {
            List<Integer> list = scriptsTable.get(scriptLoad);
            if (list != null) {
                set.addAll(list);
                Deque<Integer> deque = new LinkedList<>(list);
                while (!deque.isEmpty()) {
                    set.addAll(loadScripts(scriptsTable, deque.poll()));
                }
            }
        }
        return set;
    }
}
