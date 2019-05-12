package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Square
 * @author RomanM
 * @version 1.0 May 12, 2019
 */
public class TriangleTest {

    /**
     * test of triangle drawing
     */
    @Test
    public void whenTriangleIsDrawn() {
        Shape shape = new Triangle();
        String result = shape.draw();
        StringBuilder picture = new StringBuilder();
        picture.append("      +");
        picture.append(System.lineSeparator());
        picture.append("    +   +");
        picture.append(System.lineSeparator());
        picture.append("  +       +");
        picture.append(System.lineSeparator());
        picture.append("+ + + + + + +");
        picture.append(System.lineSeparator());
        String expect = picture.toString();
        assertThat(result, is(expect));
    }

}