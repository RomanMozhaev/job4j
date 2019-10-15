package ru.job4j.srpcalc;

public class TestInput extends ConsoleInput {
    private final String[] array;
    private int position;

    public TestInput(String[] array) {
        this.array = array;
        position = 0;
    }
    @Override
    public String ask(String string) {
        return this.array[this.position++];
    }
}
