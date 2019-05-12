package ru.job4j.pseudo;

import org.junit.Test;
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
     * the test
     */
    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()),
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

        System.setOut(stdout);
    }

}