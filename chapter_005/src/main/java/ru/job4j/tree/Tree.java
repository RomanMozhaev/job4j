package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int modCount;

    public Tree(E value) {
        root = new Node<>(value);
    }

    private int getModCount() {
        return modCount;
    }

    private Node<E> getRoot() {
        return root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentInTree = findBy(parent);
        if (parentInTree.isPresent()) {
            parentInTree.get().add(new Node<>(child));
            result = true;
            this.modCount++;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int originModCount = getModCount();
            private Queue<Node<E>> list;

            @Override
            public boolean hasNext() {
                modCheck();
                buildList();
                return list.peek() != null;
            }

            @Override
            public E next() {
                modCheck();
                buildList();
                return list.remove().getValue();
            }

            private void modCheck() {
                if (originModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
            }

            private void buildList() {
                if (list == null) {
                    list = new LinkedList<>();
                    Queue<Node<E>> data = new LinkedList<>();
                    data.offer(getRoot());
                    while (!data.isEmpty()) {
                        Node<E> el = data.poll();
                        list.offer(el);
                        for (Node<E> child : el.leaves()) {
                            data.offer(child);
                        }
                    }
                }
            }
        };
    }
}
