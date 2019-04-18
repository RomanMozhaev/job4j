package ru.job4j.condition;

/**
 * the DummyBot.
 * @author RomanM.
 * @version 1.0.
 */
public class DummyBot {
    /**
     * This method return the answer of the bot basing on the question.
     * @param question - get a question for our DummyBot.
     * @return - its answer.
     */
    public String answer(String question) {
        String result = "Это ставит меня в тупик. Задайте другой вопрос.";
        if (question.equals("Привет, Бот!")) {
            result = "Привет, Умник!";
        } else if (question.equals("Пока!")) {
            result = "До скорой встречи.";
        }
        return result;
    }
}
