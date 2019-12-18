package ru.job4j.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.dynamiclinkedlist.DynamicLinkedList;
import java.util.Iterator;

/**
 * the Thrade safe linked list which is built on Dynamic Linked list from chapter 005.
 *
 * @param <E> - generic
 */
@ThreadSafe
public class ThreadSafeLinkedList<E> implements ThreadSafeList<E> {

    /**
     * the list where we save data.
     */
    @GuardedBy("this")
    private final DynamicLinkedList<E> list;

    /**
     * the main constructor.
     */
    public ThreadSafeLinkedList() {
        this.list = new DynamicLinkedList<>();
    }

    /**
     * returns the length of the list. The method is synchronized.
     *
     * @return the integer value.
     */
    @Override
    public synchronized int getLength() {
        return this.list.getLength();
    }

    /**
     * adds new value to the list. The method is synchronized.
     *
     * @param value - adding object
     */
    @Override
    public synchronized void add(E value) {
        this.list.add(value);
    }

    /**
     * the method returns the object which has position. The method is synchronized.
     *
     * @param position - the position of the object in the list.
     * @return - the object of null.
     */
    @Override
    public synchronized E get(int position) {
        return this.list.get(position);
    }

    /**
     * the method removes the last element in the list. The method is synchronized.
     *
     * @return the removed element.
     */
    @Override
    public synchronized E removeLast() {
        return this.list.removeLast();
    }

    /**
     * the method removes the element with position in the list. The method is synchronized.
     *
     * @return the removed element.
     */
    @Override
    public synchronized E remove(int position) {
        return this.list.remove(position);
    }

    /**
     * the method returns fail-safe iterator of the list. he method is synchronized.
     *
     * @return the iterator.
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.list).iterator();
    }

    /**
     * the method creates the new copy of the list. The elements are copied with help of element serialization
     * and are added to the new list.
     *
     * @param list - the list for cloning
     * @return the cloned list.
     */
    private synchronized DynamicLinkedList<E> copy(DynamicLinkedList<E> list) {
        DynamicLinkedList<E> newList = new DynamicLinkedList<>();
        for (E e : list) {
            newList.add(e);
        }
        return newList;
    }
}
