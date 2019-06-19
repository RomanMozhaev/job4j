package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * the Function for calculation of value in diapason
 * @author RomanM
 * @version June 16, 2019
 */
public class Functions {
    /**
     * the method for value calculation
     * @param start - left diapason boarder
     * @param end - right deapason boarder
     * @param func  - functional interface for value calculation
     * @return - list of values
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            double index = i;
            list.add(func.apply(index));
        }
        return list;
    }
}
