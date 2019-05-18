package ru.job4j.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * the outside class for menu creating and inside classes for different actions.
 */
public class MenuTracker {

    /**
     * the constants for choosing of the menu options.
     */
    private static final String ADD = "0";
    private static final String REPLACE = "1";
    private static final String DELETE = "2";
    private static final String FIND_ALL = "3";
    private static final String FIND_BY_NAME = "4";
    private static final String FIND_BY_ID = "5";
    private static final String EXIT = "6";

    /**
     * the input provides user answers
     */
    private Input input;
    /**
     * the tracker contains tickets
     */
    private Tracker tracker;
    /**
     * the list of available actions
     */
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

//    public int getActionsLength() {
//        return this.actions.size();
//    }

    /**
     * the method adds all action-classes to the list.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ReplaceItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindAll());
        this.actions.add(new FindItemsByName());
        this.actions.add(new FindItemsById());
        this.actions.add(new Exit());
    }

    /**
     * the method calls the action according to the key of the command
     * @param key - the key of the command
     */
    public void select(String key) {
        int i = Integer.valueOf(key);
        this.actions.get(i).execute(this.input, this.tracker);

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
    public class AddItem implements UserAction {
        @Override
        public String key() {
            return ADD;
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding of a new ticket --------------");
            String name = input.ask("Enter the ticket name: ");
            String desc = input.ask("Enter the ticket description: ");
            long created = System.currentTimeMillis();
            Item item = new Item(name, desc, created);
            tracker.add(item);
            System.out.println("------------ The New Ticket has ID: " + item.getId() + "-----------");
        }
        @Override
        public  String info() {
            return "0. Add New Ticket";
        }
    }
    /**
     * this class replaces the ticket.
     */
    public class ReplaceItem implements UserAction {
        @Override
        public String key() {
            return REPLACE;
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
        @Override
        public  String info() {
            return "1. Edit The Ticket";
        }
    }
    /**
     * the class deletes the ticket with a corresponding ID.
     */
    public class DeleteItem implements UserAction {
        @Override
        public String key() {
            return DELETE;
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
        @Override
        public  String info() {
            return "2. Delete The Ticket";
        }
    }
    /**
     * the method prints information from all entered tickets.
     */
    public class FindAll implements UserAction {
        @Override
        public String key() {
            return FIND_ALL;
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
        @Override
        public  String info() {
            return "3. Review All Tickets";
        }
    }

    /**
     * this class finds all tickets with specific name
     */
    public class FindItemsByName implements UserAction {
        @Override
        public String key() {
            return FIND_BY_NAME;
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
        @Override
        public  String info() {
            return "4. Find  Tickets by Name";
        }
    }

    /**
     * the class finds a ticket with specific ID
     */
    public class FindItemsById implements UserAction {
        @Override
        public String key() {
            return FIND_BY_ID;
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
        @Override
        public  String info() {
            return "5. Find A Ticket by ID";
        }
    }

    /**
     * the class for closing the application.
     */
    public class Exit implements UserAction {
        @Override
        public String key() {
            return EXIT;
        }
        @Override
        public void execute(Input input, Tracker tracker) {
        }
        @Override
        public  String info() {
            return "6. Exit";
        }
    }
}


