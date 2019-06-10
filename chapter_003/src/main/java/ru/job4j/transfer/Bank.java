package ru.job4j.transfer;

import java.util.*;

/**
 * the class Bank has different methods for operation with users and their accounts.
 */
public class Bank {
    /**
     * the map where user is a key.
     */
    private Map<User, List<Account>> usersList = new HashMap<>();

    /**
     * add a new User. User has no accounts.
     * @param user - user for adding
     */
    public void addUser(User user) {
        if (user != null || !user.getName().isEmpty() || !user.getPassport().isEmpty()) {
            if (this.usersList.putIfAbsent(user, new ArrayList<>()) == null) {
                System.out.println("The user was added successfully");
            } else {
                System.out.println("The user is already exist");
            }
        } else {
            System.out.println("Not enough information for adding");
        }
    }
    /**
     * the method deletes a user from the map.
     * @param user
     */
    public void deleteUser(User user) {
        if (this.usersList.remove(user) != null) {
            System.out.println("The user was deleted successfully");
        } else {
            System.out.println("The user was not found");
        }
    }
    /**
     * it finds user by passport field
     * @param passport - user's passport
     * @return - user if it was found
     */
    private User findUserByPassport(String passport) {
        User returningUser = null;
        Iterator usersIterator = this.usersList.keySet().iterator();
        while (usersIterator.hasNext()) {
            User user = (User) usersIterator.next();
            if (user.getPassport().equals(passport)) {
                returningUser = user;
                break;
            }
        }
        return returningUser;
    }
    /**
     * it adds a new account to the user with specified passport.
     * @param passport - user's passport
     * @param account - the new account
     */
    public void addAccountToUser(String passport, Account account) {
        boolean added = false;
        User user = findUserByPassport(passport);
        if (user != null) {
            if (!this.usersList.get(user).contains(account)) {
                added = this.usersList.get(user).add(account);
            }
        }
        System.out.println(added ? "The account was added successfully" : "The account was not added");
    }
    /**
     * it deletes account of the user with specified passport
     * @param passport - user's passport
     * @param account - account for deleting
     */
    public void deleteAccountFromUser(String passport, Account account) {
        boolean added = false;
        User user = findUserByPassport(passport);
        if (user != null) {
            if (user.getPassport().equals(passport)) {
                added = this.usersList.get(user).remove(account);
            }
        }
        System.out.println(added ? "The account was deleted successfully" : "The account was not deleted");
    }
    /**
     * it provides user's account.
     * @param passport - user's passport
     * @return - user's accounts
     */
    public List<Account> getUserAccounts(String passport) {
        List accounts = new ArrayList();
        User user = findUserByPassport(passport);
        if (user != null) {
        accounts = this.usersList.get(user);
        }
        return accounts;
    }
    /**
     * it finds an account using its requisite and return account's index in the arraylist.
     * @param user - user
     * @param requisite of the required account
     * @return - index in the arraylist
     */
    private int getAccountIndex(User user, String requisite) {
        int result = -1;
        int index = 0;
        Account account;
        Iterator accounts = this.usersList.get(user).iterator();
        while (accounts.hasNext()) {
            account = (Account) accounts.next();
            if (account.getRequisites().equals(requisite)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }
    /**
     * it transfers amount from one account to another
     * @param srcPassport - first user's passport
     * @param srcRequisite of first user
     * @param destPassport second user's passport
     * @param dstRequisite of second user
     * @param amount - amount for transferring
     * @return - true if operation passed
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        User srcUser = findUserByPassport(srcPassport);
        User destUser = findUserByPassport(destPassport);
        Account srcAccount, dstAccount;
        int indexSrc = getAccountIndex(srcUser, srcRequisite);
        int indexDst = getAccountIndex(destUser, dstRequisite);
        if (srcUser != null && destUser != null && indexSrc > -1 && indexDst > -1) {
            srcAccount = this.usersList.get(srcUser).get(indexSrc);
            dstAccount = this.usersList.get(destUser).get(indexDst);
            if (srcAccount.getValue() >= amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                dstAccount.setValue(dstAccount.getValue() + amount);
                result = true;
            }
        }
        return result;
    }
}
