package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for StartUI
 */
public class StartUITest {
    /**
     * Test for add method
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }
    /**
     * Test for Replace method
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 123L));
        Input input = new StubInput(new String[]{"1", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }
    /**
     * Test for Delete method
     */
    @Test
    public void whenDeleteThenTrackerDoesNotHaveFirst() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test first", "description of first", 123L));
        Item item2 = tracker.add(new Item("test second", "description of second", 124L));
        Input input = new StubInput(new String[]{"2", item1.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test second"));
    }
    /**
     * Test for findAll method
     */
    @Test
    public void whenReviewThenTrackerLengthIs2() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test first", "description of first", 123L));
        Item item2 = tracker.add(new Item("test second", "description of second", 124L));
        Input input = new StubInput(new String[]{"3", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }
    /**
     * Test for find by name method
     */
    @Test
    public void whenFindByNameThenTestSecondTwice() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test first", "description of first", 123L));
        Item item2 = tracker.add(new Item("test second", "description of second", 124L));
        Item item3 = tracker.add(new Item("test third", "description of third", 125L));
        Item item4 = tracker.add(new Item("test second", "description of second number two", 126L));
        Item[] expect = {item2, item4};
        Input input = new StubInput(new String[]{"4", "test second", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test second"), is(expect));
    }
    /**
     * Test for find by id method
     */
    @Test
    public void whenFindByIdThenTestSecondTwice() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test first", "description of first", 123L));
        Item item2 = tracker.add(new Item("test second", "description of second", 124L));
        Item item3 = tracker.add(new Item("test third", "description of third", 125L));
        Item item4 = tracker.add(new Item("test second", "description of second number two", 126L));
        Input input = new StubInput(new String[]{"5", item3.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item3.getId()).getName(), is("test third"));
    }
}