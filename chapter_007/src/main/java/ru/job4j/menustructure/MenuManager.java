package ru.job4j.menustructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * the class for managing the menu
 */
public class MenuManager {
    /**
     * returns the list for menu printing
     * @param menu - the list of the menu items
     * @return - the list of strings for menu printing
     */
    public List<String> printMenu(List<IntName> menu) {
        String prefix = "--";
        Queue<IntName> list = new LinkedList<>();
        list.addAll(menu);
        List<String> printList = new ArrayList<>();
        IntName item = list.poll();
        while (item != null) {
            printList.add(item.printName());
            if (item.hasChildren() && item instanceof IntNameChildren) {
                IntNameChildren parent = (IntNameChildren) item;
                List<IntName> children = parent.getChildren();
                for (IntName child : children) {
                    child.addPrefix(item.getPrefix() + prefix);
                }
                Queue<IntName> temp = new LinkedList<>(list);
                list.clear();
                list.addAll(children);
                list.addAll(temp);
            }
            item = list.poll();
        }
        return printList;
    }

    /**
     * prints the list.
     * @param printList - the list of strings with prefixes and names of items.
     */
    public void print(List<String> printList) {
        for (String line : printList) {
            System.out.println(line);
        }
    }
}
