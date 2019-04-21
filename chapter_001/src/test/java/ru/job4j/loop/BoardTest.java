package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * the Test for Board class
 */
public class BoardTest {
    /**
     * it builds the board 3x3
     */
    @Test
    public void when3x3() {
        Board board = new Board();
        String result = board.paint(3, 3);
        String ln = System.lineSeparator();
        assertThat(result, is(String.format("X X%s X %sX X%s", ln, ln, ln)));
    }
    /**
     * it builds the board 5x7
     */
    @Test
    public void when5x7() {
        Board board = new Board();
        String result = board.paint(5, 7);
        String ln = System.lineSeparator();
        assertThat(result, is(String.format("X X X%s X X %sX X X%s X X %sX X X%s X X %sX X X%s", ln, ln, ln, ln, ln, ln, ln)));
    }
}
