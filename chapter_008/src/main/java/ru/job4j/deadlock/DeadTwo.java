package ru.job4j.deadlock;

public class DeadTwo implements Runnable {

        final Thread t;
        private final Object s;
        private final Object f;

        public DeadTwo(Object f, Object s) {
            this.t = new Thread(this, "DeadOne");
            this.s = s;
            this.f = f;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (f) {
                    System.out.println("first is blocked by DeadOne");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s) {
                        System.out.println("second is blocked by DeadOne");
                    }
                }

            }
        }


}
