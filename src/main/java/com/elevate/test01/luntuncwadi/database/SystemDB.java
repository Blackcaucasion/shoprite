package com.elevate.test01.luntuncwadi.database;

import com.elevate.test01.luntuncwadi.Accounts.models.CurrentAccount;
import com.elevate.test01.luntuncwadi.Accounts.models.SavingsAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 *  A representation of a simple in-memory database designed to store and manage data related to accounts.
 */
public class SystemDB {
    private static final Logger LOG = Logger.getLogger(SystemDB.class.getName());

    private static SystemDB instance;
    private Map<Long, SavingsAccount> savingsAccount;
    private Map<Long, CurrentAccount> currentAccount;

    private SystemDB(){
        savingsAccount = new HashMap<>();
        currentAccount = new HashMap<>();
//        prepopulate accounts
        savingsAccount.put(1L,new SavingsAccount(1L,1L,2000));
        savingsAccount.put(2L,new SavingsAccount(2L,2L,5000));
        currentAccount.put(3L,new CurrentAccount(3L,3L,1000,10000));
        currentAccount.put(4L,new CurrentAccount(4L,4L,-5000,20000));
    }

    public static SystemDB getInstance() {
//        ensure single instance of the class
        if(instance == null) {
            instance = new SystemDB();
        }
        return instance;
    }

    public void addSavingsAccount(SavingsAccount account) {
        savingsAccount.put(account.getId(), account);
        LOG.log(Level.INFO, "Savings account added with ID: {0}", account.getId());

    }

    public void addCurrentAccount(CurrentAccount account) {
        currentAccount.put(account.getId(), account);
        LOG.log(Level.INFO, "Current account added with ID: {0}", account.getId());

    }

    public SavingsAccount getSavingsAccount(Long accountId) {
        LOG.log(Level.INFO, "Retrieving savings account with ID: {0}", accountId);

        return savingsAccount.get(accountId);
    }

    public CurrentAccount getCurrentAccount(Long accountId) {
        LOG.log(Level.INFO, "Retrieving current account with ID: {0}", accountId);
        return currentAccount.get(accountId);
    }

}
