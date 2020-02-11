package ru.job4j.loanapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ApplicationValidation implements Validate {
    /**
     * the logger.
     */
    private final static Logger LOG = LogManager.getLogger(DataBaseConnector.class.getName());
    /**
     * the max quantity of application from one country.
     */
    private final int maxQuantity = 10;
    /**
     * the period for receiving the maxQuantity of applications.
     */
    private final long period = 1000;
    /**
     * the maximal quantity of the threads which servlet-container supports.
     */
    private final int maxThreads = 10000;
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
     * it blocks all threads which are going to add a new application to the map.
     */
    private AtomicBoolean block = new AtomicBoolean(false);
    /**
     * it allows the timer method to be sure that all threads finished adding to the map.
     */
    private Semaphore semaphore = new Semaphore(this.maxThreads);

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
        if (this.block.get()) {
            synchronized (this) {
                int r = 0;
            }
        }
        try {
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
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
        this.semaphore.release();
        return result.get();
    }

    /**
     * the method for checking pid.
     * @param pid - personal id.
     * @return message if the pid is in the black list; otherwise null;
     */
    @Override
    public String blackListChecking(int pid) {
        String message = "Sorry, you are in the black list.";
        return this.dbConnector.findInBlackList(pid) ? message : null;
    }

    /**
     * this method calls from the Timer and blocks by synchronized block this object.
     * then the this.block is set as true to not let other threads to add applications to the map.
     * Other threads goes to the if code block where they waits while this method finishes its work.
     * this.semaphore is used for waiting all threads, which may add applications to the map.
     * once no threads along the timer thread uses the map, it copies all collected applications
     * to the database and removes all elements from the map.
     * Once the copying is finished, this.block is false, synchronized block is unlocked and
     * this.semaphore releases all permits.
     */
    @Override
    public void copyToBD() {
        synchronized (this) {
            this.block.set(true);
            try {
                this.semaphore.acquire(this.maxThreads);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage(), e);
            }
            this.dbConnector.addNewApps(this.map);
            this.map.clear();
            this.block.set(false);
            this.semaphore.release(this.maxThreads);
        }
    }

    /**
     * the method for getting all applications form the data base.
     * @return array list with all loan applications.
     */
    @Override
    public ArrayList<LoanApplication> getAllAppList() {
        return this.dbConnector.findAll();
    }

    /**
     * the method for getting all applications which are tied to required pid.
     * @param pid personal id.
     * @return the array list with loan applications.
     */
    @Override
    public ArrayList<LoanApplication> getAllByPID(int pid) {
        return this.dbConnector.findAllByPID(pid);
    }
}
