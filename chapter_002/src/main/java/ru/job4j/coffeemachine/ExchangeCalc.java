package ru.job4j.coffeemachine;

import java.util.Arrays;

/**
 * the class calculates the exchange
 * @author RomanM
 * @version 1.0 May 28, 2019
 */
public class ExchangeCalc {
    /**
     * it calculates the coins for exchange
     * @param value - the bill value
     * @param price - the coffee price
     * @return - int array with coins' value
     * @throws LowValueException - if value is less than price
     */
    public int[] calc(int value, int price) throws LowValueException {
        int[] exchange = {};
        Coins[] coins = Coins.values();
        int ex = value - price;
        int index = 0;
        int indexStart;

        if (value < price) {
            throw new LowValueException();
        }
        for (Coins coin : coins) {
            if (ex >= coin.value) {
                int coinTimes = ex / coin.value;
                ex -= coin.value * coinTimes;
                indexStart = index;
                index += coinTimes;
                exchange = Arrays.copyOf(exchange, index);
                for (int i = indexStart; i < index; i++) {
                    exchange[i] = coin.value;
                }
            }
            if (ex == 0) {
                break;
            }
        }
        return exchange;
    }
}
