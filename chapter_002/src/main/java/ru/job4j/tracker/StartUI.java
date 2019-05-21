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
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * the main program cycle.
     */
    public void init() {
        List<Integer> range = new ArrayList<>();
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            menu.select(input.ask("Choose one option: ", range));
        } while (!menu.isQuit() && !"y".equals(this.input.ask("Exit?(y): ")));
    }
    /**
     * The program start.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}