package ru.job4j.calculator;
/**
 * This is the program,which calculates an ideal weight.
 */
public class Fit {
    /**
     * Идеальный вес для мужчин.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
            return (height - 100) * 1.15;
        }
        /**
         * Идеальный вес для женщины.
         * @param height Рост.
         * @return идеальный вес.
         */
        public double womanWeight(double height) {
            return (height - 110) * 1.15;
        }
}
