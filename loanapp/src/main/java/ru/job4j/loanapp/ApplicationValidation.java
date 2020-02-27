package ru.job4j.loanapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ApplicationValidation implements Validate {
    /**
     * the max quantity of application from one country.
     */
    private final int maxQuantity = 10;
    /**
     * the period for receiving the maxQuantity of applications.
     */
    private final long period = 1000;
    /**
     * the instance of this class.
     */
    private static final ApplicationValidation INSTANCE = new ApplicationValidation();
    /**
     * the Timer for copying all new applications to the database.
     */
    private final Timer timer = new Timer();
    /**
     * the map for keeping the application till the timer starts the method for copying
     * all entries to the database.
     */
    private ConcurrentHashMap<String, HashSet<LoanApplication>> map = new ConcurrentHashMap<>();
    /**
     * the instance of the database connector.
     */
    private Connector dbConnector = DataBaseConnector.getInstance();

    /**
     * the main constructor. The timer is started.
     */
    private ApplicationValidation() {
        this.timer.schedule(new TaskToCopy(), 10000, this.period);
    }

    /**
     * this class Instance getter.
     *
     * @return the instance.
     */
    public static ApplicationValidation getInstance() {
        return INSTANCE;
    }

    /**
     * the method for adding a new application to the map for temporary storage.
     *
     * @param application - the application for adding
     * @return - the message for customer notification.
     */
    @Override
    public String addApplication(LoanApplication application) {
        AtomicReference<String> result = new AtomicReference<>("The application is successfully added for reviewing.");
        String country = application.getCountry();
        this.map.compute(country, (k, v) -> {
            if (v == null) {
                v = new HashSet<>();
                v.add(application);
            } else if (v.size() < this.maxQuantity) {
                v.add(application);
            } else {
                result.set("The server is overloaded. Please try again later.");
            }
            return v;
        });
        return result.get();
    }

    /**
     * the method for checking pid.
     *
     * @param pid - personal id.
     * @return message if the pid is in the black list; otherwise null;
     */
    @Override
    public String blackListChecking(int pid) {
        String message = "Sorry, you are in the black list.";
        return this.dbConnector.findInBlackList(pid) ? message : null;
    }

    /**
     * the method copy all applications which were temporary saved in the map to the data base.
     * the tempMap keeps the link to the map instance for data saving.
     * the new empty map is assigned to this.map
     */
    @Override
    public void copyToBD() {
        ConcurrentHashMap<String, HashSet<LoanApplication>> tempMap = this.map;
        this.map = new ConcurrentHashMap<>();
        this.dbConnector.addNewApps(tempMap);
    }

    /**
     * the method for getting all applications form the data base.
     *
     * @return array list with all loan applications.
     */
    @Override
    public ArrayList<LoanApplication> getAllAppList() {
        return this.dbConnector.findAll();
    }

    /**
     * the method for getting all applications which are tied to required pid.
     *
     * @param pid personal id.
     * @return the array list with loan applications.
     */
    @Override
    public ArrayList<LoanApplication> getAllByPID(int pid) {
        return this.dbConnector.findAllByPID(pid);
    }
}
