package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    RoleStore roleStore = new RoleStore(10);


    @Before
    public void toDo() {
        roleStore.add(new Role("11"));
        roleStore.add(new Role("22"));
        roleStore.add(new Role("33"));
        roleStore.add(new Role("44"));
    }
    @Test
    public void whenFindById() {
        Role result = roleStore.findById("33");
        Role expect = new Role("33");
        assertThat(result, is(expect));
    }

    @Test
    public void whenReplace() {
        roleStore.replace("33", new Role("10"));
        Role result = roleStore.findById("10");
        Role expect = new Role("10");
        assertThat(result, is(expect));
    }

}