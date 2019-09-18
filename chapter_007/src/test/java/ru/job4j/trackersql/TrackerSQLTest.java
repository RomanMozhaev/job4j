package ru.job4j.trackersql;

import org.junit.Test;
import ru.job4j.tracker.Item;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    @Test
    public void whenAddThenAdded() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        long created = System.currentTimeMillis();
        Item item = new Item("test1", "testDescription", created);
        Item result = tracker.add(item);
        item.setId(result.getId());
        assertThat(result, is(item));
    }

    @Test
    public void whenReplaceThenReplaced() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        Item added = tracker.add(new Item("test3", "testDescription3", System.currentTimeMillis()));
        Item replacing = new Item("test4", "testDescription3", System.currentTimeMillis());
        assertThat(tracker.replace(added.getId(), replacing), is(true));

    }
    @Test
    public void whenDeleteThanDeleted() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        long created = System.currentTimeMillis();
        Item item = new Item("testDelete", "testDescription", created);
        Item result = tracker.add(item);
        String id = result.getId();
        assertThat(tracker.delete(id), is(true));
    }

    @Test
    public void whenFindAllThanFoundAll() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        long created = 12345;
        Item item = new Item("test3", "testDescription3", created);
        tracker.add(item);
        List<Item> list = tracker.findAll();
        int last = list.size() - 1;
        item.setId(list.get(last).getId());
        assertThat(list.get(last), is(item));
    }

    @Test
    public void whenFindByNameThanFoundByName() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        long created = 12345;
        Item item = new Item("tess4t4", "testDescription3", created);
        tracker.add(item);
        List<Item> list = tracker.findByName("ss4");
        int last = list.size() - 1;
        String id = list.get(last).getId();
        item.setId(id);
        assertThat(list.get(last), is(item));
        tracker.delete(id);
    }

    @Test
    public void whenFindByIdThanFoundById() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        Item expect = tracker.add(new Item("testFindByName", "testDescription3", 12345));
        String id = expect.getId();
        Item result = tracker.findById(id);
        assertThat(result, is(expect));
        tracker.delete(id);
    }

}