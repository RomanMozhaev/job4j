package ru.job4j.cash;

import org.junit.Test;

import java.lang.ref.SoftReference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CashTest {

    @Test
    public void whenAddAndGetThenAA() {
        Cash cash = new Cash();
        cash.add("a", "aa");
        SoftReference sr = cash.get("a");
        assertThat(sr.get(), is("aa"));
    }
}