package com.elevate.test01.luntuncwadi.Accounts.models;

import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

public abstract class Account {
    protected Long id;
    protected Long customerNumber;
    protected int balance;

    public Account(Long id, Long customerNumber, int balance) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.balance = balance;
    }
    public abstract void withdraw(int amountToWithdraw) throws WithdrawalAmountTooLargeException;

    public void deposit(int amountToDeposit) {
        balance += amountToDeposit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
