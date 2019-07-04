package ru.job4j.simplearraylist;

public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * delete first element of the list
     * @return
     */
    public E delete() {
        E oldData = this.first.data;
        this.first = this.first.next;
        this.size--;
        return oldData;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }


    public int getSize() {
        return this.size;
    }


    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
