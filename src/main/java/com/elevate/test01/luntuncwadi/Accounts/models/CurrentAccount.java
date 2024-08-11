package com.elevate.test01.luntuncwadi.Accounts.models;

import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

/**
 * implements Current account
 */
public class CurrentAccount extends Account{

    private int overdraftLimit;

    public CurrentAccount(Long id, Long customerNUmber, int balance, int overdraftLimit) {
        super(id, customerNUmber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(int amountToWithdraw) throws WithdrawalAmountTooLargeException {
        int availableFunds = balance + overdraftLimit;
        if (amountToWithdraw > availableFunds) {
            throw new WithdrawalAmountTooLargeException("Cannot withdraw, exceeds balance and overdraft limit");
        }
        balance -= amountToWithdraw;
    }

    public int getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(int overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
