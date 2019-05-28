package ru.job4j.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Item {
//    создание объекта стринг тоже является композицией.
    private String id;
    private String name;
    private String decs;
    private long time;

    public Item(String name, String decs, long time) {
        this.name = name;
        this.decs = decs;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    @Override
//    композиция - создаем ноый объект при вызове метода и используем его методы.
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return time == item.time
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(decs, item.decs);
    }

    @Override
    public int hashCode() {
//        Композиция
        return Objects.hash(id, name, decs, time);
    }
    @Override
    public String toString() {
//  Композиция
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
//  Композиция
        Date result = new Date(getTime());
//  Композция.
        return new StringBuilder()
                .append("Ticket's ID: " + getId())
                .append(System.lineSeparator())
                .append("Ticket's name: " + getName())
                .append(System.lineSeparator())
                .append("Ticket's description: " + getDecs())
                .append(System.lineSeparator())
                .append("Ticket's date creation: " + simple.format(result))
                .append(System.lineSeparator())
                .append("----------------------")
                .toString();

    }

}
