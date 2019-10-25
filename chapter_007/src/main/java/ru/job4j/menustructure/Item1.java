package ru.job4j.menustructure;

import java.util.List;

/**
 * item with children.
 */
public class Item1 implements IntNameChildren {
    /**
     * name - the name of the item
     * prefix - the prefix which is used with item
     * parent - item has or not children
     * children - the list of children
     */
    private String name;
    private String prefix;
    private boolean parent;
    private List<IntName> children;

    public Item1(String name, boolean parent, List<IntName> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.prefix = "";
    }

    @Override
    public List<IntName> getChildren() {
        return this.children;
    }

    @Override
    public String printName() {
        return this.prefix + this.name;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void addPrefix(String prefix) {
        this.prefix += prefix;
    }

    @Override
    public boolean hasChildren() {
        return this.parent;
    }
}
