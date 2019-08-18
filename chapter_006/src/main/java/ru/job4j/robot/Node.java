package ru.job4j.robot;

public class Node {
    private Node parent;
    private int value;
    private int x;
    private int y;
    private int grannyX;

    public Node(int x, int y, int value, Node parent, int grannyX) {
        this.parent = parent;
        this.value = value;
        this.x = x;
        this.y = y;
        this.grannyX = grannyX;
    }

    public int getGrannyX() {
        return grannyX;
    }

    public Node getParent() {
        return parent;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

