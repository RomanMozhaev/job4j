package ru.job4j.pseudo;
/**
 * the Square. It prepares the string for square printing
 * @author RomanM
 * @version 1.0 May 12, 2019
 */
public class Square implements Shape {
    /**
     * the method prepares the string for printing
     * @return string for printing
     */
    @Override
    public String draw() {
        StringBuilder picture = new StringBuilder();
        picture.append("+ + + + + + +");
        picture.append(System.lineSeparator());
        picture.append("+           +");
        picture.append(System.lineSeparator());
        picture.append("+           +");
        picture.append(System.lineSeparator());
        picture.append("+           +");
        picture.append(System.lineSeparator());
        picture.append("+ + + + + + +");
        picture.append(System.lineSeparator());
        return picture.toString();
    }
}
