package ru.job4j.pseudo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test for Paint
 *  * @author RomanM
 *  * @version 1.0 May 12, 2019
 */
public class PaintTest {
    /**
     * the fields for managing the output
     */
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream stdout = System.out;

    /**
     * switch the output to the memory
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * return the output to the console
     */
    @After
    public void backOutput() {
        System.setOut(stdout);
    }

    /**
     * the test for printing of Square
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(new String(this.out.toByteArray()),
                is(new StringBuilder()
                        .append("+ + + + + + +")
                        .append(System.lineSeparator())
                        .append("+           +")
                        .append(System.lineSeparator())
                        .append("+           +")
                        .append(System.lineSeparator())
                        .append("+           +")
                        .append(System.lineSeparator())
                        .append("+ + + + + + +")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    /**
     * the test for printing of Triangle
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(new String(this.out.toByteArray()),
                is(new StringBuilder()
                        .append("      +")
                        .append(System.lineSeparator())
                        .append("    +   +")
                        .append(System.lineSeparator())
                        .append("  +       +")
                        .append(System.lineSeparator())
                        .append("+ + + + + + +")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

}