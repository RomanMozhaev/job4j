package ru.job4j.loanapp;

/**
 * the class for keeping and sending application info between program modules.
 */
public class LoanApplication {

    /**
     * the customer first name.
     */
    private String name;
    /**
     * the customer second name.
     */
    private String surname;
    /**
     * the country which was defined by IP.
     */
    private String country;
    /**
     * customer personal ID.
     */
    private int pid;
    /**
     * the term in months.
     */
    private int term;
    /**
     * the required loan sum.
     */
    private int sum;


    public LoanApplication(String country, String name, String surname, int pid, int term, int sum) {
        this.country = country;
        this.name = name;
        this.surname = surname;
        this.pid = pid;
        this.term = term;
        this.sum = sum;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPid() {
        return pid;
    }

    public int getTerm() {
        return term;
    }

    public int getSum() {
        return sum;
    }
}
