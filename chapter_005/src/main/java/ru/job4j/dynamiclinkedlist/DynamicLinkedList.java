package ru.job4j.dynamiclinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicLinkedList<E> implements Iterable<E> {

    private int modCount;
    private int length;
    private Node<E> lastAdded;
    private Node<E> firstAdded;

    private int getModCount() {
        return this.modCount;
    }

    public int getLength() {
        return this.length;
    }

    private Node<E> getFirstAdded() {
        return this.firstAdded;
    }


    public void add(E value) {
        Node<E> node = new Node<>(null, value, null);
        if (this.length == 0) {
            this.lastAdded = node;
            this.firstAdded = this.lastAdded;
        } else {
            this.lastAdded.next = node;
            node.previous = this.lastAdded;
            this.lastAdded = node;
        }
        this.modCount++;
        this.length++;
    }

    public E get(int position) {
        return getNode(position).element;
    }

    public E removeLast() {
        E result = this.lastAdded.element;
        if (this.lastAdded == this.firstAdded) {
            this.lastAdded = null;
            this.firstAdded = null;
        } else {
            this.lastAdded.previous.next = null;
            this.lastAdded = this.lastAdded.previous;
        }
        this.modCount++;
        this.length--;
        return result;
    }

    public E remove(int position) {
        Node<E> node = getNode(position);
        if (this.firstAdded == this.lastAdded) {
            this.firstAdded = null;
            this.lastAdded = null;
        } else {
            if (node == firstAdded) {
                firstAdded.next.previous = null;
                firstAdded = firstAdded.next;
            } else {
                if (node == lastAdded) {
                    lastAdded.previous.next = null;
                    lastAdded = lastAdded.previous;
                } else {
                    Node<E> prev = node.previous;
                    Node<E> next = node.next;
                    prev.next = next;
                    next.previous = prev;
                }
            }
        }
        this.modCount++;
        this.length--;
        return node.element;
    }

    private Node<E> getNode(int position) {
        if (position >= this.length && position < 0) {
            throw new NullPointerException();
        }
        int index = 0;
        Node<E> node = this.firstAdded;
        while (index != position) {
            node = node.next;
            index++;
        }
        return node;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int expectedModCount = getModCount();
            private int length = getLength();
            private int index;
            private Node<E> node = getFirstAdded();

            @Override
            public boolean hasNext() {
                modCheck();
                boolean result = false;
                if (length > index) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                modCheck();
                if (!hasNext()) {
                    throw new NullPointerException();
                }
                E result = node.element;
                node = node.next;
                index++;
                return result;
            }

            private void modCheck() {
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(Node<E> previous, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
}
