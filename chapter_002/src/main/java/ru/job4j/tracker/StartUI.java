package ru.job4j.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class creates the user interface for working with ticket's system.
 */
public class StartUI {

    private static final String ADD = "0";
    private static final String REPLACE = "1";
    private static final String DELETE = "2";
    private static final String FIND_ALL = "3";
    private static final String FIND_BY_NAME = "4";
    private static final String FIND_BY_ID = "5";
    private static final String EXIT = "6";
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Choose one option: ");
            switch (answer) {
                case ADD:
                    addItem();
                    break;
                case REPLACE:
                    replaceItem();
                    break;
                case DELETE:
                    deleteItem();
                    break;
                case FIND_ALL:
                    findAllItems();
                    break;
                case FIND_BY_NAME:
                    findItemsByName();
                    break;
                case FIND_BY_ID:
                    findItemById();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Please try again.");
            }
        }
    }

    /**
     * This method adds a new ticket to the array.
     */
    private void addItem() {
        System.out.println("------------ Adding of a new ticket --------------");
        String name = this.input.ask("Enter the ticket name: ");
        String desc = this.input.ask("Enter the ticket description: ");
        long created = System.currentTimeMillis();
        Item item = new Item(name, desc, created);
        this.tracker.add(item);
        System.out.println("------------ The New Ticket has ID: " + item.getId() + "-----------");
    }

    /**
     * this method replaces the ticket.
     */
    private void replaceItem() {
        System.out.println("------------ Editing of the ticket --------------");
        String id = this.input.ask("Enter the ID:");
        Item item = this.tracker.findById(id);
        if (item == null) {
            System.out.println("The ticket was not found.");
        } else {
            String name = this.input.ask("Enter a new name: ");
            String desc = this.input.ask("Enter a new description: ");
            long created = System.currentTimeMillis();
            item = new Item(name, desc, created);
            item.setId(id);
            if (this.tracker.replace(id, item)) {
                System.out.println("------------ The changes were entered successfully ------------");
            } else {
                System.out.println("------------ the operation failure. Please try again ------------");
            }
        }
    }

    /**
     * the method deletes the ticket with a corresponding ID.
     */
    private void deleteItem() {
        System.out.println("------------ Deleting of the ticket --------------");
        String id = this.input.ask("Enter the ID:");
        if (this.tracker.delete(id)) {
            System.out.println("The delete operation was completed successfully");
        } else {
            System.out.println("the operation failure. The ticket with the entered ID doesn't exist.");
        }
    }
    /**
     * the method prints information from all entered tickets.
     */
    private void findAllItems() {
        System.out.println("------------ All tickets review --------------");
        Item[] items = this.tracker.findAll();
        ticketPrinting(items);
    }
    /**
     *
     */
    private void findItemsByName() {
        System.out.println("------------ Finding all tickets with a specific name --------------");
        String specName = this.input.ask("Enter the name for searching: ");
        Item[] items = this.tracker.findByName(specName);
        System.out.println("------------ The tickets with the specific name review --------------");
        ticketPrinting(items);
    }

    /**
     * this method prints the array of tickets
     * @param items - the array of tickets for printing
     */
    private void ticketPrinting(Item[] items) {
        if (items.length == 0) {
            System.out.println("No ticket found.");
        } else {
            for (int i = 0; i < items.length; i++) {
                System.out.println("Order number: " + (i + 1));
                System.out.println("Ticket's ID: " + items[i].getId());
                System.out.println("Ticket's name: " + items[i].getName());
                System.out.println("Ticket's description: " + items[i].getDecs());
                DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
                Date result = new Date(items[i].getTime());
                System.out.println("Ticket's date creation: " + simple.format(result));
                System.out.println("----------------------");
            }
        }
    }

    /**
     * the method searches the ticket by ID
     */
    private void findItemById() {
        System.out.println("------------ Finding the ticket by ID --------------");
        String id = this.input.ask("Enter the ID for searching: ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            Item[] items = new Item[1];
            items[0] = item;
            ticketPrinting(items);
        } else {
            System.out.println("No ticket found.");
        }

    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("0. Add New Ticket");
        System.out.println("1. Edit The Ticket");
        System.out.println("2. Delete The Ticket");
        System.out.println("3. Review All Tickets");
        System.out.println("4. Find  Tickets by Name");
        System.out.println("5. Find A Ticket by ID");
        System.out.println("6. Exit");
    }

    /**
     * The program start.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}