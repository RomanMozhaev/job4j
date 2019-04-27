package ru.job4j.condition;

/**
 * This class calculates the area of triangle.
 * @author RomanM
 * @version 1.0 April 19, 2019.
 */
public class Triangle {
    /**
     * this method calculates the area of the triangle.
     * @param x1 - abscissa of the first point.
     * @param y1 - ordinata of the first point.
     * @param x2 - abscissa of the second point.
     * @param y2 - ordinata of the second point.
     * @param x3 - abscissa of the third point.
     * @param y3 - ordinata of the third point.
     * @return - if the triangle does not exist - return -1; if it exists - return its area.
     */
    public double area(int x1, int y1, int x2, int y2, int x3, int y3) {
        double a = new Point().distance(x1, y1, x2, y2);
        double b = new Point().distance(x3, y3, x2, y2);
        double c = new Point().distance(x1, y1, x3, y3);
        if (!triangleExist(a, b, c)) {
            return -1D;
        }
        double p = period(a, b, c);
        //triangle area calculating
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;
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
