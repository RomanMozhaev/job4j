package ru.job4j.hascycle;

public class Node<T> {
    public T value;
    public Node<T> next;
    public boolean mark = false;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}