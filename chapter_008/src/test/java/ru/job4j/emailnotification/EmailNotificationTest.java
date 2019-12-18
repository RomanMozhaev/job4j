package ru.job4j.emailnotification;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailNotificationTest {

    @Test
    public void whenThen() {
        EmailNotification emailNotification = new EmailNotification();
        for (int i = 0; i < 50; i++) {
            emailNotification.emailTo(new User("Colin " + i, "colin@email.com " + i));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}