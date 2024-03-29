package ru.job4j.condition;

/**
 * This class calculates the area of triangle.
 * @author RomanM
 * @version 1.0 April 19, 2019.
 */
public class Triangle {
    private Point first;
    private Point second;
    private Point third;

    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third = cp;
    }
    /**
     * this method calculates the area of the triangle.
     * @return - if the triangle does not exist - return -1; if it exists - return its area.
     */
    public double area() {
        double a = first.distance(second);
        double b = second.distance(third);
        double c = third.distance(first);
        if (!triangleExist(a, b, c)) {
            return -1D;
        }
        double p = period(a, b, c);
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * This method checks the triangle exists or not.
     * @param a - first leg.
     * @param b - second leg.
     * @param c - third leg.
     * @return - true - exists, false - does not exist.
     */
    private boolean triangleExist(double a, double b, double c) {
        if (a == 0 || b == 0 || c == 0 || ((a + b) <= c) || ((a + c) <= b) || ((b + c) <= a)) {
            return false;
        }
        return true;
    }

    /**
     * This method returns the half of the triangle perimeter.
     * @param a - first leg.
     * @param b - second leg.
     * @param c - third leg.
     * @return the half if the perimeter.
     */
    public double period(double a, double b, double c) {
        return (a + b + c) / 2;
    }

}
