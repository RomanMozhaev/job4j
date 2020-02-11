package ru.job4j.loanapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * the interface for database connectors.
 */
public interface Connector {

    void addNewApps(Map<String, HashSet<LoanApplication>> map);

    boolean findInBlackList(int pid);

    ArrayList<LoanApplication> findAllByPID(int pid);

    ArrayList<LoanApplication> findAll();

}
