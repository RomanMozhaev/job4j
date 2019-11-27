package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final double limit;
    private boolean direction = true;

    public RectangleMove(Rectangle rect, double limit) {
        this.rect = rect;
        this.limit = limit - rect.getWidth();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (this.direction && this.rect.getX() <= this.limit) {
                movement(1);
            } else {
                this.direction = false;
            }
            if (!this.direction && this.rect.getX() >= 0) {
                movement(-1);
            } else {
                this.direction = true;
            }
        }
    }

    private void movement(int value) {
        this.rect.setX(this.rect.getX() + value);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}