package ru.job4j.masksearch;

import java.util.List;

public class TreeElement {

    private List<Character> maskCh;
    private List<Character> fileCh;
    private int depth;
    private boolean left;

    public TreeElement(List<Character> maskCh, List<Character> fileCh, int depth, boolean left) {
        this.maskCh = maskCh;
        this.fileCh = fileCh;
        this.depth = depth;
        this.left = left;
    }

    public List<Character> getMaskCh() {
        return maskCh;
    }

    public List<Character> getFileCh() {
        return fileCh;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isLeft() {
        return left;
    }
}
