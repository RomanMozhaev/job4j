package ru.job4j.emailnotification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the class for emailing
 */
public class EmailNotification {
    /**
     * the Thread pool.
     */
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );
    /**
     * subject pattern.
     */
    String subject = "Notification {username} to email {email}.";
    /**
     * email body pattern.
     */
    String body = "Add a new event to {username}.";

    /**
     * the method for email sending.
     * @param user - the email recipient.
     */
    public void emailTo(User user) {
        String firstMod = this.subject.replaceAll("\\{username\\}", user.getName());
        final String userSubject = firstMod.replaceAll("\\{email\\}", user.getEmail());
        final String userBody = this.body.replaceAll("\\{username\\}", user.getName());
        this.pool.submit(() -> send(userSubject, userBody, user.getEmail()));
    }

    /**
     * the method for shutting down the pool.
     */
    public void close() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * the method for sending the email.
     * *****under construction*****
     * @param subject
     * @param body
     * @param email
     */
    public void send(String subject, String body, String email) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(subject);
        System.out.println(body);
        System.out.println(email);
        System.out.println();
    }


}
