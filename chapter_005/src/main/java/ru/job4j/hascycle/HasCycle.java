package ru.job4j.hascycle;

public class HasCycle<T> {

    public boolean hasCycle(Node<T> first) {
        Node<T> turtle = first;
        Node<T> hare = first;
        boolean result = false;
        while (hare != null && turtle != null) {
            turtle = turtle.next;
            hare = hare.next.next;
            if (turtle == hare) {
                result = true;
                break;
            }
        }
        return result;
    }
}

