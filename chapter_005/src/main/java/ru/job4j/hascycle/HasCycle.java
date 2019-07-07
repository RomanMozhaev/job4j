package ru.job4j.hascycle;

public class HasCycle<T> {

    public boolean hasCycle(Node<T> first) {
        boolean result = false;
        Node<T> node = first;
        while (node != null) {
            if (node.mark) {
                result = true;
                break;
            }
            node.mark = true;
            node = node.next;
        }
        return result;
    }
}

