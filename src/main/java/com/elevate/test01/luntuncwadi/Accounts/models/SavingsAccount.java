package com.elevate.test01.luntuncwadi.Accounts.models;

import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

public class SavingsAccount {
    private Long id;
    private Long customerNumber;
    private int balance;

    public SavingsAccount(Long id, Long customerNumber, int balance) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.balance = balance;
    }

    public void withdraw(int amountToWithdraw) throws WithdrawalAmountTooLargeException {
        if(balance-amountToWithdraw < 2000) {
            throw new WithdrawalAmountTooLargeException("You do not have  enough funds to withdraw");
        }
        balance -=amountToWithdraw;
    }

    public void deposit(int amountToDeposit) {
        balance +=amountToDeposit;
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
