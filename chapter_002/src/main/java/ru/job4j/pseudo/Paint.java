package ru.job4j.pseudo;
/**
 * the Paint paints the shape
 * @author RomanM
 * @version 1.0 May 12, 2019
 */
public class Paint {
    /**
     * the method draws the shape
     * @param shape - the figure for drawing
     */
    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }
}
