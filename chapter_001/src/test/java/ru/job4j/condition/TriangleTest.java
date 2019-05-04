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
        Point first = new Point(1, 1);
        Point second = new Point(1, 1);
        Point third = new Point(4, 7);
        Triangle triangle = new Triangle(first, second, third);
        double area = triangle.area();
        assertThat(area, is(-1D));
    }

    /**
     * Test. Triangle is a Rectangle.
     * a leg = 10.
     * b leg = 10.
     */
    @Test
    public void whenExist1ThenArea50() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 10);
        Point third = new Point(10, 0);
        Triangle triangle = new Triangle(first, second, third);
        double area = triangle.area();
        assertThat(area, closeTo(50, 0.1));
    }

    /**
     * Test. Some triangle.
     */
    @Test
    public  void whenExist2ThenArea14() {
        Point first = new Point(1, 1);
        Point second = new Point(5, 5);
        Point third = new Point(8, 1);
        Triangle triangle = new Triangle(first, second, third);
        double area = triangle.area();
        assertThat(area, closeTo(14, 0.1));
    }
    /**
     * Test. The triangle became a line
     */
    @Test
    public void whenLineThenNoTriangle() {
        Point first = new Point(1, 1);
        Point second = new Point(5, 1);
        Point third = new Point(8, 1);
        Triangle triangle = new Triangle(first, second, third);
        double area = triangle.area();
        assertThat(area, is(-1D));
    }
}
