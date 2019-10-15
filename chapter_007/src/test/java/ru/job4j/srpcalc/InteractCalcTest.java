package ru.job4j.srpcalc;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InteractCalcTest {

    @Test
    public void whenAddThenFive() {
        String[] answers = {"1", "2", "3", "5"};
        Input input = new TestInput(answers);
        InteractCalc calc = new InteractCalc(input);
        calc.start();
        assertThat(calc.getResult(), is(5D));
    }
    @Test
    public void whenSubThenTwo() {
        String[] answers = {"2", "3", "1", "5"};
        Input input = new TestInput(answers);
        InteractCalc calc = new InteractCalc(input);
        calc.start();
        assertThat(calc.getResult(), is(2D));
    }
    @Test
    public void whenSubAndAddThenTen() {
        String[] answers = {"2", "3", "1", "1", "y", "8", "5"};
        Input input = new TestInput(answers);
        InteractCalc calc = new InteractCalc(input);
        calc.start();
        assertThat(calc.getResult(), is(10D));
    }
    @Test
    public void whenMultAndDivThenFive() {
        String[] answers = {"4", "3", "5", "3", "y", "3", "5"};
        Input input = new TestInput(answers);
        InteractCalc calc = new InteractCalc(input);
        calc.start();
        assertThat(calc.getResult(), is(5D));
    }
}