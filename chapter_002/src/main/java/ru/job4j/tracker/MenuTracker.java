package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * the outside class for menu creating and inside classes for different actions.
 */
public class MenuTracker {

    /**
     * the constants for choosing of the menu options.
     */
//    композиция - объекты стринг
    private static final String ADD = "0";
    private static final String REPLACE = "1";
    private static final String DELETE = "2";
    private static final String FIND_ALL = "3";
    private static final String FIND_BY_NAME = "4";
    private static final String FIND_BY_ID = "5";
    private static final String EXIT = "6";

    private boolean quit;

    /**
     * the input provides user answers
     */
//    компазиция . повторно используем класс интерфейса
    private Input input;
    /**
     * the tracker contains tickets
     */
    //    компазиция . повторно используем класс трекер
    private Tracker tracker;
    /**
     * the list of available actions
     */
//    агригация
    private List<UserAction> actions = new ArrayList<>();

    /**
     * the constructor assigns input and tracker fields.
     * @param input
     * @param tracker
     */

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public boolean isQuit() {
        return quit;
    }

    private void setQuit(Boolean quit) {
        this.quit = quit;
    }

    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * the method adds all action-classes to the list.
     */
    public void fillActions() {
//        агригация классов по одному интерфейсу
        this.actions.add(new AddItem("Add New Ticket", ADD));
        this.actions.add(new ReplaceItem("Edit The Ticket", REPLACE));
        this.actions.add(new DeleteItem("Delete The Ticket", DELETE));
        this.actions.add(new FindAll("Review All Tickets", FIND_ALL));
        this.actions.add(new FindItemsByName("Find  Tickets by Name", FIND_BY_NAME));
        this.actions.add(new FindItemsById("Find A Ticket by ID", FIND_BY_ID));
        this.actions.add(new Exit("Exit", EXIT));
    }

    /**
     * the method calls the action according to the key of the command
     * @param key - the key of the command
     */
    public void select(int key) {
//        использование метода после агрегации
        this.actions.get(key).execute(this.input, this.tracker);

    }

    /**
     * the method prints the menu of the actions.
     */
    public void show() {
        System.out.println("Menu:");
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
    }
    /**
     * This class adds a new ticket to the array.
     */
//    все внутренние классы наследуются от БейсикЭкшен
    public class AddItem extends BaseAction {

        public AddItem(String info, String key) {
            super(info, key);
        }

        @Override
//        композиция Инпут и Трекер
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding of a new ticket --------------");
            String name = input.ask("Enter the ticket name: ");
            String desc = input.ask("Enter the ticket description: ");
            long created = System.currentTimeMillis();
    //       композиция айтем
            Item item = new Item(name, desc, created);
            tracker.add(item);
            System.out.println("------------ The New Ticket has ID: " + item.getId() + "-----------");
        }
    }
    /**
     * this class replaces the ticket.
     */
    public class ReplaceItem extends BaseAction {

        public ReplaceItem(String info, String key) {
            super(info, key);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Editing of the ticket --------------");
            String id = input.ask("Enter the ID:");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("The ticket was not found.");
            } else {
                String name = input.ask("Enter a new name: ");
                String desc = input.ask("Enter a new description: ");
                long created = System.currentTimeMillis();
                item = new Item(name, desc, created);
                item.setId(id);
                if (tracker.replace(id, item)) {
                    System.out.println("------------ The changes were entered successfully ------------");
                } else {
                    System.out.println("------------ the operation failure. Please try again ------------");
                }
            }
        }
    }
    /**
     * the class deletes the ticket with a corresponding ID.
     */
    public class DeleteItem extends BaseAction {

        public DeleteItem(String info, String key) {
            super(info, key);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Deleting of the ticket --------------");
            String id = input.ask("Enter the ID:");
            if (tracker.delete(id)) {
                System.out.println("The delete operation was completed successfully");
            } else {
                System.out.println("the operation failure. The ticket with the entered ID doesn't exist.");
            }
        }
    }
    /**
     * the method prints information from all entered tickets.
     */
    public class FindAll extends BaseAction {

        public FindAll(String info, String key) {
            super(info, key);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ All tickets review --------------");
            Item[] items = tracker.findAll();
            if (items.length == 0) {
                System.out.println("No ticket found.");
            } else {
                for (int i = 0; i < items.length; i++) {
                    System.out.println("Order number: " + (i + 1));
                    System.out.println(items[i].toString());
                }
            }
        }
    }

    /**
     * this class finds all tickets with specific name
     */
    public class FindItemsByName extends BaseAction {

        public FindItemsByName(String info, String key) {
            super(info, key);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Finding all tickets with a specific name --------------");
            String specName = input.ask("Enter the name for searching: ");
            Item[] items = tracker.findByName(specName);
            System.out.println("------------ The tickets with the specific name review --------------");
            if (items.length == 0) {
                System.out.println("No ticket found.");
            } else {
                for (int i = 0; i < items.length; i++) {
                    System.out.println("Order number: " + (i + 1));
                    System.out.println(items[i].toString());
                }
            }
        }
    }

    /**
     * the class finds a ticket with specific ID
     */
    public class FindItemsById extends BaseAction {

        public FindItemsById(String info, String key) {
            super(info, key);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Finding the ticket by ID --------------");
            String id = input.ask("Enter the ID for searching: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item.toString());
            } else {
                System.out.println("No ticket found.");
            }
        }
    }

    /**
     * the class for closing the application.
     */
    public class Exit extends BaseAction {

        public Exit(String info, String key) {
            super(info, key);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            setQuit(true);
        }
    }
}


