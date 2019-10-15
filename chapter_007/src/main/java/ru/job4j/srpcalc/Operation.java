package ru.job4j.srpcalc;

/**
 * the abstract class for all operations
 */
public abstract class Operation implements IOperation {
    /**
     * desc - description of the operation
     * num - the order number of the operation
     */
    private final String desc;
    private final int num;

    /**
     * the constructor for applying the fields
     *
     * @param desc - description
     * @param num  - order number
     */
    public Operation(String desc, int num) {
        this.desc = desc;
        this.num = num;
    }

    /**
     * Getter for the description
     *
     * @return description
     */
    @Override
    public String getDesc() {
        return this.desc;
    }

    /**
     * Getter for the order number
     *
     * @return order number
     */
    @Override
    public int getNum() {
        return this.num;
    }

}
