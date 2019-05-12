package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Square
 * @author RomanM
 * @version 1.0 May 12, 2019
 */
public class SquareTest {

    /**
     * test of square drawing
     */
    @Test
    public void whenSquareIsDrawn() {
        Shape shape = new Square();
        String result = shape.draw();
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
        String expect = picture.toString();
        assertThat(result, is(expect));
    }

}