package ru.job4j.simplehashmap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author RomanM
 * @version July 13, 2019
 * The SimpleHashMap bases on array. Default length is 16;
 * Index calculates as key's hashcode % length
 * If the array's cell with index is not empty, SimpleHashMap does not add the new element and return false.
 * @param <K> - key
 * @param <V> - value
 */
public class SimpleHashMap<K, V> {

    private Object[] map;
    private int length = 16;
    private int elementsQuantity;
    private int modCount;

    public SimpleHashMap() {
        this.map = new Object[this.length];
    }

    public SimpleHashMap(int length) {
        this.length = length;
        this.map = new Object[this.length];
    }

    private int getModCount() {
        return modCount;
    }

    public int getLength() {
        return length;
    }

    private Object[] getMap() {
        return map;
    }

    public boolean insert(K key, V value) {
        if (this.elementsQuantity > this.length / 2) {
            extendArray();
        }
        Element element = new Element(key, value);
        boolean result = false;
        int index = getIndex(key);
        if (this.map[index] == null) {
            this.map[index] = element;
            result = true;
            this.elementsQuantity++;
            this.modCount++;
        }
        return result;
    }
    public V get(K key) {
        V result = null;
        int keyHash = key.hashCode();
        Element element = (Element) this.map[getIndex(key)];
        if (element != null && element.getHashCode() == keyHash && Objects.equals(element.getKey(), key)) {
            result = element.getValue();
        }
        return result;
    }
    public boolean delete(K key) {
        boolean result = false;
        int index = getIndex(key);
        Element element = (Element) this.map[index];
        if (element != null && Objects.equals(element.getKey(), key)) {
            this.map[index] = null;
            this.elementsQuantity--;
            this.modCount++;
            result = true;
        }
        return result;
    }

    private int getIndex(K key) {
        return key == null ? 0 : key.hashCode() % this.length;
    }

    private void extendArray() {
        this.length *= 2;
        int index;
        Object[] mapTemp = this.map;
        this.map = new Object[length];
        for (Object element : mapTemp) {
            if (element != null) {
                index = ((Element) element).getHashCode() == 0 ? 0 : ((Element) element).getHashCode() % this.length;
                this.map[index] = element;
            }
        }
    }

    public Iterator<Element> iterator() {
        return new Iterator<Element>() {

            private final int originModCount = getModCount();
            private int index = 0;

            @Override
            public boolean hasNext() {
                modCheck();
                boolean result = false;
                while (index < getLength()) {
                    if (getMap()[index] != null) {
                        result = true;
                        break;
                    }
                    index++;
                }
                return result;
            }

            @Override
            public Element next() {
                modCheck();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index++;
                return (Element) getMap()[index - 1];
            }

            private void modCheck() {
                if (originModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    class Element {
        private final K key;
        private final V value;
        private final int hashCode;
        public Element(K key, V value) {
            this.key = key;
            this.value = value;
            this.hashCode = key.hashCode();
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHashCode() {
            return hashCode;
        }
    }
}
