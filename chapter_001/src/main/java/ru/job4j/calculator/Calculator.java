package ru.job4j.calculator;

/**this is a simple calculator.
 * @author RomanM.
 * @version 1.0.
 */

public class Calculator {
    /**
     * this method calculates a sum of two digits.
     * @param first  - first digit.
     * @param second - second digit.
     * @return - return the result of the operation.
     */
    public double add(double first, double second) {
        return first + second;
    }
    /**
     * this method calculates a sum of two digits.
     * @param first  - first digit.
     * @param second - second digit.
     * @return - return the result of the operation.
     */
    public double subtrack(double first, double second) {
        return first - second;
    }
    /**
     * this method divides first digit one by second digit.
     * @param first  - first digit.
     * @param second - second digit.
     * @return - return the result of the operation.
     */
    public double div(double first, double second) {
        return first / second;
    }
    /**
     * this method multiple two digits.
     * @param first  - first digit.
     * @param second - second digit.
     * @return - return the result of the operation.
     */
    public double multiple(double first, double second) {
        return first * second;
    }
}
