package ru.job4j.robot;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RobotTest {
    @Test
    public void whenMatrixThenWay() {
        int[][] matrix =  {
                {1, 10, 1},
                {2, 10, 1},
                {3, 2, 1}
        };
        int sx = 0;
        int sy = 0;
        int fx = 1;
        int fy = 2;

        Robot robot = new Robot();
        List<Integer> way = robot.optimalWay(matrix, sx, sy, fx, fy);
        List<Integer> expect = List.of(1, 2, 3, 2, 1, 1);
        assertThat(numAndSpace(way), is(numAndSpace(expect)));
    }

    private String numAndSpace(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (Integer i: list) {
            builder.append(i);
            builder.append(" ");
        }
        return builder.toString();
    }
}