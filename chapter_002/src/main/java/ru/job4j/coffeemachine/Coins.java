package ru.job4j.coffeemachine;

public enum Coins {

    TEN(10), FIVE(5), TWO(2), ONE(1);

    public int value;

    Coins(int value) {
        this.value = value;
    }
}
