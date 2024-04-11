package com.elevate.test01.luntuncwadi.Accounts;

import com.elevate.test01.luntuncwadi.exceptions.AccountNotFoundException;
import com.elevate.test01.luntuncwadi.exceptions.WithdrawalAmountTooLargeException;

public interface AccountService {
    /**
     * Opens a savings account
     * @param accountId
     * @param amountToDeposit
     */
    void openSavingsAccount(Long accountId, Long amountToDeposit);

    /**
     * Opens a current account
     * @param accountId
     */
    void openCurrentAccount(Long accountId);

    /***
     * withdraw money from account
     * @param accountId
     * @param amountToWithdraw
     */
    void withdraw(Long accountId,int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException;

    /***
     * deposit money into account
     * @param accountId
     * @param amountToDeposit
     */
    void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException;

}
