package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.log;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FunctionsTest {

    Functions functions = new Functions();
    /**
     * test for Linear Function
     */
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = this.functions.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D, 17D);
        assertThat(result, is(expected));
    }
    /**
     * test for quadratic Function
     */
    @Test
    public void whenQuadraticFunction() {
        List<Double> result = this.functions.diapason(5, 8, x -> x * x + 1);
        List<Double> expected = Arrays.asList(26D, 37D, 50D, 65D);
        assertThat(result, is(expected));
    }
    /**
     * test for logarithmic Function
     */
    @Test
    public void whenLogarithmicFunction() {
        List<Double> answer = this.functions.diapason(5, 8, x -> log(x) + 1);
        List<Double> result = new ArrayList<>();
        for (Double element: answer) {
            element = ((double) Math.round(element * 100)) / 100;
            result.add(element);
        }
        List<Double> expect = Arrays.asList(2.61D, 2.79D, 2.95D, 3.08D);
        assertThat(result, is(expect));
    }


}