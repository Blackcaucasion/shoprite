package com.elevate.test01.luntuncwadi.Accounts.impl;

import com.elevate.test01.luntuncwadi.Accounts.AccountService;
import com.elevate.test01.luntuncwadi.Accounts.models.SavingsAccount;
import com.elevate.test01.luntuncwadi.database.SystemDB;
import com.elevate.test01.luntuncwadi.exceptions.AccountNotFoundException;
import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SavingsAccountService implements AccountService {
    private static final Logger LOG = Logger.getLogger(CurrentAccountService.class.getName());
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private SystemDB dbConnection = SystemDB.getInstance();

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

    }

    @Override
    public void withdraw(Long accountId, int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException {
        int attempts = 0;
        while (attempts < MAX_RETRY_ATTEMPTS) {
            try {
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
        SavingsAccount savingsAccount = dbConnection.getSavingsAccount(accountId);
        if (savingsAccount != null) {
            savingsAccount.deposit(amountToDeposit);

            return; // Deposit successful, exit the method
        }
        // Account not found
        throw new AccountNotFoundException("Account not found with ID: " + accountId);
    }
}
