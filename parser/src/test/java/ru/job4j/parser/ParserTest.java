package ru.job4j.parser;

import org.junit.Test;
import java.util.Calendar;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void whenParserThenVacationIs() {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        date.set(year, Calendar.AUGUST, 5, 0, 1, 1);
        String url = "https://www.sql.ru/forum/job/2";
        Parser parser = new Parser(date.getTimeInMillis());
        parser.parse(url);
        Map<String, Vacation> map = parser.getVacations();
        String string = "Вакансия Java Developer Full-Stack удаленно";
        Vacation vac = map.get(string);
        assertThat(vac.getName(), is(string));
    }

}