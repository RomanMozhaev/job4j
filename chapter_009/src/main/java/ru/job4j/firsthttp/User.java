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
    private final String photoId;
    private final String password;
    private final String role;

    /**
     * this constructor is user for updating the user.
     *  @param id    - the id of the user
     * @param name  - the name of the user
     * @param email -the email of the user
     * @param photoId - the link to the photo
     */
    public User(int id, String name, String email, String photoId, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoId = photoId;
        this.createDate = -1;
        this.password = password;
        this.role = role;
    }

    /**
     * this constructor is used for creating a new user.
     *  @param name  - the name of the user
     * @param email - the email of the user
     * @param photoId - the link to the photo
     */
    public User(String name, String email, String photoId, String password, String role) {
        this.photoId = photoId;
        this.id = -1;
        this.name = name;
        this.email = email;
        this.createDate = System.currentTimeMillis();
        this.password = password;
        this.role = role;
    }

    /**
     * this constructor is used for deleting a new user.
     *
     * @param id - the id of the user.
     */
    public User(int id) {
        this.id = id;
        this.photoId = null;
        this.name = null;
        this.email = null;
        this.createDate = -1;
        this.password = null;
        this.role = null;
    }

    /**
     * the constructor for adding to the map
     *  @param id         - the id of the user
     * @param name       - the name of the user
     * @param email      -  the email of the user
     * @param createDate - the date of user creating
     * @param photoId
     */
    public User(int id, String name, String email, long createDate, String photoId, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.photoId = photoId;
        this.password = password;
        this.role = role;
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

    public String getPhotoId() {
        return photoId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
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

