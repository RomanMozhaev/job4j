package ru.job4j.foodstorage;

/**
 * the class is used for returning the prepared answers for testing.
 */
public class TestInput extends ConsoleInput {
    private final String[] array;
    private int position;

    public TestInput(String[] array) {
        this.array = array;
        position = 0;
    }

    /**
     * @param string - the question
     * @return next string from prepared array.
     */
    @Override
    public String ask(String string) {
        return this.array[this.position++];
    }
}