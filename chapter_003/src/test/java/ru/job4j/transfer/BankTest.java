package ru.job4j.transfer;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankTest {
    /**
     * test. we add one User and associate two accounts and check the value of first account.
     */
    @Test
    public void whenAddTwoThenFindAccounts() {
        Bank bank = new Bank();
        bank.addUser(new User("Patrik", "1234"));
        bank.addAccountToUser("1234", new Account("887766", 40.0D));
        bank.addAccountToUser("1234", new Account("887767", 20.0D));
        List<Account> accounts = bank.getUserAccounts("1234");
        assertThat(accounts.get(0).getValue(), is(40.0D));
    }

    /**
     * test. we add two Users and associate one accout for each. Then we delete first user and check
     * that the method finds empty Accounts array.
     */
    @Test
    public void whenAddTwoThenDeleteFirst() {
        Bank bank = new Bank();
        bank.addUser(new User("Patrik", "1234"));
        bank.addUser(new User("Alex", "6789"));
        bank.addAccountToUser("1234", new Account("887766", 40.0D));
        bank.addAccountToUser("6789", new Account("887767", 20.0D));
        bank.deleteUser(new User("Patrik", "1234"));
        assertThat(bank.getUserAccounts("1234").isEmpty(), is(true));
    }

    /**
     * Test. We add one user and associate two accounts. Then we delete first account and check that Accounts array
     * does not contain it.
     */
    @Test
    public void whenAddTwoAccountsThenDeleteFirst() {
        Bank bank = new Bank();
        bank.addUser(new User("Patrik", "1234"));
        bank.addAccountToUser("1234", new Account("887766", 40.0D));
        bank.addAccountToUser("1234", new Account("887767", 20.0D));
        bank.deleteAccountFromUser("1234", new Account("887766", 30.0D));
        assertThat(bank.getUserAccounts("1234").contains(new Account("887766", 40.0D)), is(false));
    }

    /**
     * test. we transfer from one account to another successfully
     */
    @Test
    public void whenTransferFromFirstToSecondAccount() {
        Bank bank = new Bank();
        bank.addUser(new User("Patrik", "1234"));
        bank.addAccountToUser("1234", new Account("887766", 40.0D));
        bank.addAccountToUser("1234", new Account("887767", 20.0D));
        bank.transferMoney("1234", "887766", "1234", "887767", 30.0D);
        assertThat(bank.getUserAccounts("1234").get(1).getValue(), is(50.0D));
    }

    /**
     * test. Transferring does not pass
     */
    @Test
    public void whenTransferIsFault() {
        Bank bank = new Bank();
        bank.addUser(new User("Patrik", "1234"));
        bank.addAccountToUser("1234", new Account("887766", 40.0D));
        bank.addAccountToUser("1234", new Account("887767", 20.0D));
        bank.transferMoney("1234", "887766", "1234", "887767", 41.0D);
        assertThat(bank.getUserAccounts("1234").get(1).getValue(), is(20.0D));
    }


}