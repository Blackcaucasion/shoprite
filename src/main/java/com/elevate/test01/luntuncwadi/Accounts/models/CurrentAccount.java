package com.elevate.test01.luntuncwadi.Accounts.models;

import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

/**
 * implements Current account
 */
public class CurrentAccount {
    private Long id;
    private Long customerNUmber;
    private int balance;
    private int overdraftLimit;

    public CurrentAccount(Long id, Long customerNUmber, int balance, int overdraftLimit) {
        this.id = id;
        this.customerNUmber = customerNUmber;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    public void withdraw(int amountToWithdraw) throws WithdrawalAmountTooLargeException {
        int availableFunds = balance + overdraftLimit;
        if (amountToWithdraw > availableFunds) {
            throw new WithdrawalAmountTooLargeException("Cannot withdraw, exceeds balance and overdraft limit");
        }
        balance -= amountToWithdraw;
    }

    public void deposit(int amountToDeposit) {
        balance += amountToDeposit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerNUmber() {
        return customerNUmber;
    }

    public void setCustomerNUmber(Long customerNUmber) {
        this.customerNUmber = customerNUmber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(int overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
