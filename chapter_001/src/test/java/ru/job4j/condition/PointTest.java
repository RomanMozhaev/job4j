package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
/**
 * Test for Point.java.
 * @author RomanM
 * @version 1.1 April 30, 2019
 */
public class PointTest {
    /**
     * Test for the method "distance".
     */
    @Test
    public void whenZeroAndTenThenTen() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 10);
        double result = first.distance(second);
        assertThat(result, is(10D));
    }

    /**
     * Test for the method distance with the result as a floating-point number.
     */
    @Test
    public void whenThisCoordinate1ThenThatDistance1() {
        Point first = new Point(5, 6);
        Point second = new Point(7, 8);
        double result = first.distance(second);
        assertThat(result, closeTo(2.82, 0.01));
    }
    /**
     * Test for the method distance with the result as a floating-point number.
     */
    @Test
    public void whenThisCoordinate2ThenThatDistance2() {
        Point first = new Point(10, 6);
        Point second = new Point(7, 15);
        double result = first.distance(second);
        assertThat(result, closeTo(9.48, 0.01));
    }
}
