package ru.job4j.simplequeue;

import ru.job4j.stack.SimpleStack;

public class SimpleQueue<T> {

private SimpleStack<T> stack1 = new SimpleStack<>();
private SimpleStack<T> stack2 = new SimpleStack<>();

    public T poll() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
            stack2.push(stack1.poll());
            }
        }
        return stack2.poll();
    }

    public void push(T value) {
        stack1.push(value);
    }
}
