package ru.job4j.matrix2list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class for working with matrix
 */
public class Matrix {
    /**
     * the method creates Integer list from Integer matrix/
     * @param matrix - Integer matrix for transferring
     * @return - Integer list
     */
    public List<Integer> create(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(e -> Stream.of(e)).collect(Collectors.toList());
//        return Arrays.stream(matrix).flatMap(e -> Arrays.stream(e)).collect(Collectors.toList());
    }
}
