package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Test for Triangle.
 * @version 1.0 April 19, 2019.
 */

public class TriangleTest {
    /**
     * Test.
     * Triangle does not exist. Point 1 and Point 2 are equal.
     */
    @Test
    public void whenDoesNotExistThenMinusOne() {
        Triangle triangle = new Triangle();
        double area = triangle.area(1, 1, 1, 1, 4, 7);
        assertThat(area, is(-1D));
    }

    /**
     * Test. Triangle is a Rectangle.
     * a leg = 10.
     * b leg = 10.
     */
    @Test
    public void whenExist1ThenArea50() {
        Triangle triangle = new Triangle();
        double area = triangle.area(0, 0, 0, 10, 10, 0);
        assertThat(area, closeTo(50, 0.1));
    }

    /**
     * Test. Some triangle.
     */
    @Test
    public  void whenExist2ThenArea14() {
        Triangle triangle = new Triangle();
        double area = triangle.area(1, 1, 5, 5, 8, 1);
        assertThat(area, closeTo(14, 0.1));
    }
}
