package ru.job4j.srpcalc;

/**
 * the interface of the operations
 */
public interface IOperation {
    /**
     * executes the operation
     */
    void execute();

    /**
     * Getter for description
     *
     * @return
     */
    String getDesc();

    /**
     * Getter for order number
     *
     * @return
     */
    int getNum();
}
