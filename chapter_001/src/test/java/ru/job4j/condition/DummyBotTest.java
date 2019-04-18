package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for DummyBot.
 * @version 1.0.
 */

public class DummyBotTest {

    /**
     * The bot answers the question "Привет, Бот!"
     */
    @Test
    public void whenPrivetThenPrivetUmnik() {
        DummyBot bot = new DummyBot();
        String answer = bot.answer("Привет, Бот!");
        String checkAnswer = "Привет, Умник!";
        assertThat(answer, is(checkAnswer));
    }

    /**
     * The bot answers the question "Пока!"
     */
    @Test
    public void whenPokaThenPokaUmnik() {
        DummyBot bot = new DummyBot();
        String answer = bot.answer("Пока!");
        String checkAnswer = "До скорой встречи.";
        assertThat(answer, is(checkAnswer));
    }

    /**
     * The bot answers the question "Бот, какое сегодя число?"
     */
    @Test
    public void whenOtherThenTupik() {
        DummyBot bot = new DummyBot();
        String answer = bot.answer("Бот, какое сегодня число?");
        String checkAnswer = "Это ставит меня в тупик. Задайте другой вопрос.";
        assertThat(answer, is(checkAnswer));
    }
}
