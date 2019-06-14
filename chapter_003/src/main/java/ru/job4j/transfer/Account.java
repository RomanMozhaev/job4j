package ru.job4j.transfer;

import java.util.Objects;

public class Account {

    private double value;
    private String requisites;

    public Account(String requisites, double value) {
        this.value = value;
        this.requisites = requisites;
    }

    public String getRequisites() {
        return requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean wire(Account dstAccount, double amount) {
        boolean result = false;
        if (this.getValue() >= amount) {
            this.setValue(this.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
