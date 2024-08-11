package com.elevate.test01.luntuncwadi.Accounts.models;

import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

public class SavingsAccount extends Account {

    private static final int MIN_BALANCE = 2000;

    public SavingsAccount(Long id, Long customerNumber, int balance) {
        super(id, customerNumber, balance);
        if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException("Initial deposit must be at least " + MIN_BALANCE);
        }
    }

    public void withdraw(int amountToWithdraw) throws WithdrawalAmountTooLargeException {
        if (balance - amountToWithdraw < MIN_BALANCE) {
            throw new WithdrawalAmountTooLargeException("You do not have  enough funds to withdraw");
        }
        balance -= amountToWithdraw;
    }

}
