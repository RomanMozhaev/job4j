package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExchangeCalcTest {
    /**
     * value is 50
     * price is 35
     * exchange is 10, 5
     */
    @Test
    public void when50and35then10and5() {
        ExchangeCalc calc = new ExchangeCalc();
        int[] result = {};
        try {
            result = calc.calc(50, 35);
        } catch (LowValueException lwe) {
            System.out.println("Value is less than price");
        }
        int[] expect = {10, 5};
        assertThat(result, is(expect));
    }

    /**
     * value is 100
     * price is 31
     * exchange is 10 x6 , 5 x1, 2 x2
     */
    @Test
    public void when100and31() {
        ExchangeCalc calc = new ExchangeCalc();
        int[] result = {};
        try {
            result = calc.calc(100, 31);
        } catch (LowValueException lwe) {
            System.out.println("Value is less than price");
        }
        int[] expect = {10, 10, 10, 10, 10, 10, 5, 2, 2};
        assertThat(result, is(expect));
    }
}