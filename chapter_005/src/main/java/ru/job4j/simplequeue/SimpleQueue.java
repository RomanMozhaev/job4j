package ru.job4j.simplequeue;

import ru.job4j.stack.SimpleStack;

public class SimpleQueue<T> {

private SimpleStack<T> stack1 = new SimpleStack<>();
private SimpleStack<T> stack2 = new SimpleStack<>();

    public T poll() {
        T result = null;
        int phase = 0;
        while (phase != 2) {
            if (phase == 0) {
                stack2.push(stack1.poll());
                if (stack1.isEmpty()) {
                    result = stack2.poll();
                    phase = 1;
                }
            }
            if (phase == 1) {
                stack1.push(stack2.poll());
                if (stack2.isEmpty()) {
                    phase = 2;
                }
            }
        }
        return result;


//        while (!stack1.isEmpty()) {
//            stack2.push(stack1.poll());
//        }
//        T result = stack2.poll();
//        while (!stack2.isEmpty()) {
//            stack1.push(stack2.poll());
//        }
//        return result;
    }

    public void push(T value) {
        stack1.push(value);
    }
}
