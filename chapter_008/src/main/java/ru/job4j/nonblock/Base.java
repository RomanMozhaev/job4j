package ru.job4j.nonblock;

/**
 * the class which describes models.
 */
public class Base {
    /**
     * the model id.
     */
    private int id;
    /**
     * the version of the model modification.
     */
    private int version;
    /**
     * some useful data.
     */
    private int data;

    /**
     * the main constructor.
     *
     * @param id   - the model id.
     * @param data - the useful data.
     */
    public Base(int id, int data) {
        this.id = id;
        this.version = 0;
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void updateVersion() {
        this.version++;
    }
}