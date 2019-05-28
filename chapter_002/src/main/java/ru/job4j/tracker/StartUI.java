package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the user interface for working with ticket's system.
 */
public class StartUI {

    /**
     * receiving of user input
     */
    private final Input input;

    /**
     * ticket's storage
     */
    private final Tracker tracker;

    /**
     * Constructor
     * @param input receiving of user input initialisation.
     * @param tracker ticket's storage initialisation.
     */
//    копмозиция Инпут и Трекер
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * the main program cycle.
     */
    public void init() {
//        агрегация Интегер
        List<Integer> range = new ArrayList<>();
//        композиция менюТракер
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            menu.select(input.ask("Choose one option: ", range));
//            Композиция статического класса Стринг и использованием статического метода иквалс?
        } while (!menu.isQuit() && !"y".equals(this.input.ask("Exit?(y): ")));
    }
    /**
     * The program start.
     * @param args
     */
//    агрегация массива объектов стринг
    public static void main(String[] args) {
//        композиция стартУИ и т.д.
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}