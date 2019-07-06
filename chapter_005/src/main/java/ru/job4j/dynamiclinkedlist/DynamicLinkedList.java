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
        if (this.length == 0) {
            this.lastAdded = new Node<>(null, value, null);
            this.firstAdded = new Node<>(null, value, null);
        }
        if (this.length == 1) {
            this.lastAdded.element = value;
            this.lastAdded.previous = this.firstAdded;
            this.firstAdded.next = this.lastAdded;
        } else {
            Node<E> node = this.lastAdded;
            this.lastAdded = new Node<>(node, value, null);
            node.next = this.lastAdded;
        }
        this.modCount++;
        this.length++;
    }

    public E get(int position) {
        return getNode(position).element;
    }

    public E remove(int position) {
        Node<E> node = getNode(position);
        if (this.length == 1) {
            this.firstAdded = null;
            this.lastAdded = null;
        }
        if (this.length == 2) {
            lastAdded.previous = null;
            firstAdded.next = null;
            if (position == 0) {
                firstAdded = lastAdded;
            } else {
                lastAdded = firstAdded;
            }
        }
        if (this.length > 2) {
            if (position == 0) {
                this.firstAdded = this.firstAdded.next;
                this.firstAdded.previous = null;
            }
            if (position == this.length - 1) {
                this.lastAdded = this.lastAdded.previous;
                this.lastAdded.next = null;
            }
            if (position > 0 && position < this.length - 1) {
                Node<E> prev = node.previous;
                Node<E> next = node.next;
                prev.next = next;
                next.previous = prev;
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
        return new Iterator<E>() {

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

    private static class Node<E> {
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
