package ru.job4j.loanapp;

import java.util.TimerTask;

/**
 * the task for the timer.
 * The main task is to copy collected data in the map since previous copying
 * to the database.
 */
public class TaskToCopy extends TimerTask {

    final Thread t;

    public TaskToCopy() {
        this.t = new Thread(this, "TimerTask");
        this.t.start();
    }

    @Override
    public void run() {
        ApplicationValidation validation = ApplicationValidation.getInstance();
        validation.copyToBD();
    }
}
