package ru.job4j.condition;

/**
 * This is the program, which calculates the distance between two points on cartesian plane.
 */

public class Point {
    /**
     * The method for calculation the distance between two points.
     * @param x1 - x-coordinate of first point.
     * @param y1 - y-coordinate of second point.
     * @param x2 - x-coordinate of first point.
     * @param y2 - y-coordinate of second point.
     * @return the distance
     */
    public double distance(int x1, int y1, int x2, int y2) {
        double xPow = Math.pow(x1 - x2, 2);
        double yPow = Math.pow(y1 - y2, 2);
        return Math.sqrt(xPow + yPow);
    }
}
