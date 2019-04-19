package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
/**
 * Test for Point.java.
 */
public class PointTest {
    /**
     * Test for the method "distance".
     */
    @Test
    public void whenZeroAndTenThenTen() {
        Point point = new Point();
        double result = point.distance(0, 0, 0, 10);
        assertThat(result, is(10D));
    }

    /**
     * Test for the method distance with the result as a floating-point number.
     */
    @Test
    public void whenThisCoordinate1ThenThatDistance1() {
        Point point = new Point();
        double result = point.distance(5, 6, 7, 8);
        assertThat(result, closeTo(2.82, 0.01));
    }
    /**
     * Test for the method distance with the result as a floating-point number.
     */
    @Test
    public void whenThisCoordinate2ThenThatDistance2() {
        Point point = new Point();
        double result = point.distance(10, 6, 7, 15);
        assertThat(result, closeTo(9.48, 0.01));
    }
}
