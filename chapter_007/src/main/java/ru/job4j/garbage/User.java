package ru.job4j.garbage;

public class User {
    private String name ;
    private long l = 9L;


    public User(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("kaput");
    }
}
