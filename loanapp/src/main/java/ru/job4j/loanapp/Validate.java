package ru.job4j.loanapp;

import java.util.ArrayList;

/**
 * the interface for validation.
 */
public interface Validate {

    String addApplication(LoanApplication application);

    String blackListChecking(int pid);

    void copyToBD();

    ArrayList<LoanApplication> getAllAppList();

    ArrayList<LoanApplication> getAllByPID(int pid);

}
