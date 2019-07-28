package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenUrlThenUrl() {
        Config config = new Config("/home/roman/Projects/job4j/app.properties");
        config.load();
        String result = config.value("hibernate.connection.url");
        assertThat(result, is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenPasswordThenPassword() {
        Config config = new Config("/home/roman/Projects/job4j/app.properties");
        config.load();
        String result = config.value("hibernate.connection.password");
        assertThat(result, is("password"));
    }
}