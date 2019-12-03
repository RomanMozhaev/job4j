package ru.job4j.userstorage;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransferThenTransferred() {
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 1000);
        User user2 = new User(2, 1000);
        userStorage.add(user1);
        userStorage.add(user2);
        assertTrue(userStorage.transfer(1, 2, 500));
    }

}