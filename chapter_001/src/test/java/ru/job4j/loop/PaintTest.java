package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Pyramid
 */
public class PaintTest {
    /**
     * Test for the triangle with right side hypothenuse
     */
    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String result = paint.rightTrl(4);
        System.out.println(result);
        assertThat(result, is(
                new StringJoiner(ln, "", ln)
                .add("^   ")
                .add("^^  ")
                .add("^^^ ")
                .add("^^^^")
                .toString()
                )
        );
    }

    /**
     * Test for the triangle with left side hypothenuse
     */
    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String result = paint.leftTrl(4);
        System.out.println(result);
        assertThat(result, is(
                new StringJoiner(ln, "", ln)
                        .add("   ^")
                        .add("  ^^")
                        .add(" ^^^")
                        .add("^^^^")
                        .toString()
                )
        );
    }

    /**
     * Test for Pyramid. the height is 4
     */
    @Test
    public void whenPyramid4Full() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String result = paint.pyramid(4);
        System.out.println(result);
        assertThat(result, is(
                new StringJoiner(ln, "", ln)
                        .add("   ^   ")
                        .add("  ^^^  ")
                        .add(" ^^^^^ ")
                        .add("^^^^^^^")
                        .toString()
                )
        );
    }
    /**
     * Test for Pyramid. the height is 5.
     */
    @Test
    public void whenPyramid5Full() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String result = paint.pyramid(5);
        System.out.println(result);
        assertThat(result, is(
                new StringJoiner(ln, "", ln)
                        .add("    ^    ")
                        .add("   ^^^   ")
                        .add("  ^^^^^  ")
                        .add(" ^^^^^^^ ")
                        .add("^^^^^^^^^")
                        .toString()
                )
        );
    }
}
