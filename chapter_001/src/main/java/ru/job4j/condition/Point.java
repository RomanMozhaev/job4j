package ru.job4j.condition;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * This is the program, which calculates the distance between two points on cartesian plane.
 * @author RomanM
 * @version 1.1 April 30, 2019
 */

public class Point {
    int x;
    int y;

    public Point(int first, int second) {
        this.x = first;
        this.y = second;
    }
    /**
     * The method for calculation the distance between two points.
     * @param that - second point.
     * @return the distance
     */
    public double distance(Point that) {
        return sqrt(pow(this.x - that.x, 2) + pow(this.y - that.y, 2));
    }
}
