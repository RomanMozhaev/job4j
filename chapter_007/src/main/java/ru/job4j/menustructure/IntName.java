package ru.job4j.menustructure;

/**
 * the basic interface.
 */
public interface IntName {
    /**
     * returns a name with prefix if it exists.
     * @return the string combined from prefix and name.
     */
    String printName();

    /**
     * adds prefix for each level of the tree depth.
     * @param prefix - the prefix which is used in the program
     */
    void addPrefix(String prefix);

    /**
     * returns the prefix which is already applied for the item
     * @return the string with prefix
     */
    String getPrefix();

    /**
     * returns the answer has or does not have the item children.
     * @return true if it has, or false if not.
     */
    boolean hasChildren();
}
