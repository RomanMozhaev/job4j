package ru.job4j.calculate;
/**
 * This is the program Calculate that asks you to say Hello World!
 * @author Roman Mozhaev
 */
public class Calculate {
    /**
     * This is a main method. It prints Say Hello World
     */
    public static void main(String[] args) {
        System.out.println("Say Hello World!");
    }
    /**
     * Method echo.
     * @param name Your name.
     * @return Echo plus your name.
     */
    public String echo(String name) {
        return "Echo, echo, echo : " + name;
    }
}
