package ru.job4j.dynamiclinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicLinkedList<E> implements Iterable<E> {

    private int modCount;
    private int nextIndex;
    private Node<E> first = new Node<>(null, null, null);
    private Node<E> last = new Node<>(null, null, null);

    private int getModCount() {
        return this.modCount;
    }

    private int getNextIndex() {
        return this.nextIndex;
    }

    private Node<E> getLast() {
        return this.last;
    }


    public void add(E value) {
        switch (this.nextIndex) {
           case  0 :
               this.first.element = value;
               this.last.element = value;
               break;
            case 1 :
                this.first.element = value;
                this.first.previous = this.last;
                this.last.next = this.first;
                break;
            default:
                Node<E> node = this.first;
                this.first = new Node<>(node, value, null);
                node.next = this.first;
                break;
        }
        this.modCount++;
        this.nextIndex++;
    }

    public E get(int position) {
        return getNode(position).element;
    }

    public E remove(int position) {
        Node<E> node = getNode(position);
        Node<E> prev = node.previous;
        Node<E> next = node.next;
        prev.next = next;
        next.previous = prev;
        return node.element;
    }

    private Node<E> getNode(int position) {
        if (position >= this.nextIndex && position < 0) {
            throw new NullPointerException();
        }
        int index = 0;
        Node<E> node = this.last;
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
            private int length = getNextIndex();
            private int index;
            private Node<E> node = getLast();

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
