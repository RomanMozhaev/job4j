package ru.job4j.sort;

/**
 * the User contains fields Age and Name
 */
public class User implements Comparable<User> {
    private String name;
    private Integer age;

    User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        int result = this.age.compareTo(o.age);
        return result != 0 ? result : this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
