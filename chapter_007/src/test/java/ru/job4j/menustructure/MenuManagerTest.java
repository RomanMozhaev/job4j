package ru.job4j.menustructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MenuManagerTest {

    @Test
    public void whenThen() {
        IntName item1 = new Item2("First", false);
        IntName item2 = new Item2("Second", false);
        IntName item3 = new Item2("Third", false);
        IntName item7 = new Item2("Forth One One", false);
        IntName item8 = new Item2("Forth One Two", false);
        List<IntName> listChildren2 = new ArrayList<>();
        listChildren2.add(item7);
        listChildren2.add(item8);
        IntNameChildren item5 = new Item1("Forth One", true, listChildren2);
        IntName item6 = new Item2("Forth two", false);
        List<IntName> listChildren1 = new ArrayList<>();
        listChildren1.add(item5);
        listChildren1.add(item6);
        IntNameChildren item4 = new Item1("Forth", true, listChildren1);
        IntName item9 = new Item2("Fifth", false);
        List<IntName> list = new ArrayList<>();
        list.addAll(List.of(item1, item2, item3, item4, item9));
        MenuManager manager = new MenuManager();
        manager.print(manager.printMenu(list));
    }

}