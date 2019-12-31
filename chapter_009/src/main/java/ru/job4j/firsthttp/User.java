package ru.job4j.firsthttp;

import java.util.Objects;

/**
 * the class of user.
 */
public class User {

    private final int id;
    private final String name;
    private final String email;
    private final long createDate;

    /**
     * this constructor is user for updating the user.
     *
     * @param id    - the id of the user
     * @param name  - the name of the user
     * @param email -the email of the user
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = -1;
    }

    /**
     * this constructor is used for creating a new user.
     *
     * @param name  - the name of the user
     * @param email - the email of the user
     */
    public User(String name, String email) {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.createDate = System.currentTimeMillis();
    }

    /**
     * this constructor is used for deleting a new user.
     *
     * @param id - the id of the user.
     */
    public User(int id) {
        this.id = id;
        this.name = null;
        this.email = null;
        this.createDate = -1;
    }

    /**
     * the constructor for adding to the map
     *
     * @param id         - the id of the user
     * @param name       - the name of the user
     * @param email      -  the email of the user
     * @param createDate - the date of user creating
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}

