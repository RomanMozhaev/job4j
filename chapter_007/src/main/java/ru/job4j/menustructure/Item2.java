package ru.job4j.menustructure;

/**
 * item without children
 */
public class Item2 implements IntName {

    /**
     * name - the name of the item
     * prefix - the prefix which is used with item
     * parent - item has or not children
     */
    private String name;
    private String prefix;
    private boolean parent;

    public Item2(String name, boolean parent) {
        this.name = name;
        this.parent = parent;
        this.prefix = "";
    }

    @Override
    public String printName() {
        return this.prefix + this.name;
    }

    @Override
    public void addPrefix(String prefix) {
        this.prefix = this.prefix + prefix;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public boolean hasChildren() {
        return this.parent;
    }
}
