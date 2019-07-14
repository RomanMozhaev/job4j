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

        if (parentInTree.isPresent() && findBy(child).isEmpty()) {
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

    public boolean isBinary() {
        boolean result = true;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            Optional<Node<E>> node = findBy(it.next());
            List<Node<E>> leaves = node.get().leaves();
            if (!leaves.isEmpty() && leaves.size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int originModCount = -1;
            private List<Node<E>> list = new LinkedList<>(List.of(getRoot()));
            private int index = 0;

            @Override
            public boolean hasNext() {
                modCheck();
                boolean result = false;
                if (this.list.size() > this.index) {
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
                this.index++;
                return list.get(this.index - 1).getValue();
            }

            private void modCheck() {
                if (this.originModCount == -1) {
                    buildList();
                    this.originModCount = getModCount();
                }
                if (originModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
            }

            private void buildList() {
                int i = 0;
                while (this.list.size() > i) {
                    this.list.addAll(this.list.get(i).leaves());
                    i++;
                }
            }
        };
    }
}
