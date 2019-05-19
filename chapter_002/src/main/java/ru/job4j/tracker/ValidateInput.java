package ru.job4j.tracker;

import java.util.List;

public class ValidateInput extends ConsoleInput {

    public int ask(String string, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(string, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select the valid menu option.");
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong enter. Please try again.");
            }
        } while (invalid);
        return value;
    }
}
