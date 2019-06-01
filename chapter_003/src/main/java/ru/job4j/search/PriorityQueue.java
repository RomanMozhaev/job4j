package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int index = 0;
        if (this.tasks.size() == 0) {
            this.tasks.add(task);
        } else {
            for (Task t : this.tasks) {
                if (t.getPriority() > task.getPriority()) {
                    this.tasks.add(index, task);
                    break;
                }
                index++;
            }
        }
        if (index == this.tasks.size()) {
            this.tasks.add(index, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
