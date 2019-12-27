package ru.job4j.firsthttp;

/**
 * the class of user.
 */
public class User {

    private final int id;
    private final String name;
    private final String email;
    private final long createDate;

    public User(int id, String name, String email, long createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateDate() {
        return createDate;
    }


}

