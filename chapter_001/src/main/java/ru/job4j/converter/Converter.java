package ru.job4j.converter;

/**
 * Корвертор валюты.
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     * Обменный курс 70 руб. за 1 евро.
     */
    public int rubleToEuro(int value) {
        int exchageRate = 70;
        return value / exchageRate;
    }
    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return рубли.
     * Обменный курс 70 руб. за 1 евро.
     */
    public int euroToRuble(int value) {
        int exchageRate = 70;
        return value * exchageRate;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     * Обменный курс 60 руб за 1 дол.
     */
    public int rubleToDollar(int value) {
        int exchageRate = 60;
        return value / exchageRate;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return рубли
     * Обменный курс 60 руб за 1 дол.
     */
    public int dollarToRuble(int value) {
        int exchageRate = 60;
        return value * exchageRate;
    }

}
