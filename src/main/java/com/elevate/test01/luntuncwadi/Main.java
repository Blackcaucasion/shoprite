package com.elevate.test01.luntuncwadi;

import com.elevate.test01.luntuncwadi.Accounts.AccountService;
import com.elevate.test01.luntuncwadi.Accounts.impl.AccountServiceImpl;
import com.elevate.test01.luntuncwadi.exceptions.AccountNotFoundException;
import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

/***
 * Testing scenarios for the different functions
 */
public class Main {
//NB create Scanner for user input testing
    public static void main(String[] args) {
        // Create an instance of AccountServiceImpl
        AccountService accountService = new AccountServiceImpl();
        // Test openCurrentAccount method
        accountService.openCurrentAccount(102L);
        System.out.println("Current account opened successfully");
        // Test deposit method for Current Account
        try {
            accountService.deposit(102L, 1000);
            System.out.println("Deposit into Current Account successful.");
        } catch (AccountNotFoundException e) {
            System.out.println("Current Account not found.");
        }
        // Test withdraw method for Current Account
        try {
            accountService.withdraw(102L, 1500);
            System.out.println("Withdrawal from Current Account successful.");
        } catch (AccountNotFoundException e) {
            System.out.println("Current Account not found.");
        } catch (WithdrawalAmountTooLargeException e) {
            System.out.println("Withdrawal amount too large for Current Account.");
        }

        // Test openSavingsAccount method
        try {
            accountService.openSavingsAccount(101L, 10000L);
            System.out.println("Current account opened successfully");
        } catch ( Exception e){
            e.printStackTrace();
            return;
        }

        // Test deposit method for Savings Account
        try {
            accountService.deposit(101L, 500);
            System.out.println("Deposit into Savings Account successful.");
        } catch (AccountNotFoundException e) {
            System.out.println("Savings Account not found.");
        }
        // Test withdraw method for Savings Account
        try {
            accountService.withdraw(101L, 200);
            System.out.println("Withdrawal from Savings Account successful.");
        } catch (AccountNotFoundException e) {
            System.out.println("Savings Account not found.");
        } catch (WithdrawalAmountTooLargeException e) {
            System.out.println("Withdrawal amount too large for Savings Account.");
        }

    }

}
