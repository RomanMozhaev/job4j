package ru.job4j.masksearch;

public class Letter {

    private Integer divPoint;
    private Character ch;

    public Letter(Integer divPoint, Character ch) {
        this.divPoint = divPoint;
        this.ch = ch;
    }

    public Integer getDivPoint() {
        return divPoint;
    }

    public Character getCh() {
        return ch;
    }
}
