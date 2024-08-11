package com.elevate.test01.luntuncwadi.Accounts.impl;

import com.elevate.test01.luntuncwadi.Accounts.AccountService;
import com.elevate.test01.luntuncwadi.Accounts.models.CurrentAccount;
import com.elevate.test01.luntuncwadi.Accounts.models.SavingsAccount;
import com.elevate.test01.luntuncwadi.database.SystemDB;
import com.elevate.test01.luntuncwadi.exceptions.AccountNotFoundException;
import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService { // need one service implementing the AccountService
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class.getName());
    private SystemDB dbConnection = SystemDB.getInstance();
    private static final int MAX_RETRY_ATTEMPTS = 3;


    @Override
    public void openSavingsAccount(Long accountId, Long amountToDeposit) {

                if (amountToDeposit < 2000) {
            LOG.log(Level.WARNING, "Cannot open a savings account with less than R2000.00 deposit.");
           throw  new RuntimeException("Deposit amount less than R2000");

        }

        dbConnection.addSavingsAccount(new SavingsAccount(accountId, 1L, amountToDeposit.intValue()));
        LOG.log(Level.INFO, "Savings account opened successfully with ID: {0}", accountId);
    }

    @Override
    public void openCurrentAccount(Long accountId) {
        dbConnection.addCurrentAccount(new CurrentAccount(accountId, 1L, 0, 0));
        LOG.log(Level.INFO, "Current account opened successfully with ID: {0}", accountId);

    }

    @Override
    public void withdraw(Long accountId, int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException {
        int attempts = 0;
        while (attempts < MAX_RETRY_ATTEMPTS) {
            try {
                // Check if it's a current account
                CurrentAccount currentAccount = dbConnection.getCurrentAccount(accountId);
                if (currentAccount != null) {
                    currentAccount.withdraw(amountToWithdraw);
                    return; // Withdrawal successful, exit the loop
                }
                // Check if it's a savings account
                SavingsAccount savingsAccount = dbConnection.getSavingsAccount(accountId);
                if (savingsAccount != null) {
                    savingsAccount.withdraw(amountToWithdraw);
                    return; // Withdrawal successful, exit the loop
                }


                // Account not found
                throw new AccountNotFoundException("Account not found with ID: " + accountId);
            } catch (WithdrawalAmountTooLargeException e) {
                attempts++;
                LOG.log(Level.WARNING, "Withdrawal failed due to amount being too large. Retrying attempt", attempts);
            }
        }

        throw new WithdrawalAmountTooLargeException("Maximum retry attempts reached, unable to withdraw.");
    }

    @Override
    public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException {

        // Check if it's a current account
        CurrentAccount currentAccount = dbConnection.getCurrentAccount(accountId);
        if (currentAccount != null) {
            currentAccount.deposit(amountToDeposit);
            return; // Deposit successful, exit the method
        }
                SavingsAccount savingsAccount = dbConnection.getSavingsAccount(accountId);
        if (savingsAccount != null) {
            savingsAccount.deposit(amountToDeposit);

            return; // Deposit successful, exit the method
        }
        // Account not found
        throw new AccountNotFoundException("Account not found with ID: " + accountId);

    }
}
