package ru.job4j.matrix2list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class MatrixTest {

    @Test
    public void whenMatrixThenList() {
        Integer[][] intMatrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
        Matrix matrix = new Matrix();
        List<Integer> result = matrix.create(intMatrix);
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(result, is(expect));
    }
 }