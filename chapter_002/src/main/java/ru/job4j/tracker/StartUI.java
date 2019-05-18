package ru.job4j.tracker;

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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Choose one option: "));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }
    /**
     * The program start.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}