package com.elevate.test01.luntuncwadi.exceptions;

/**
 * Custom exception for withdrawing a too large amount.
 */
public class WithdrawalAmountTooLargeException extends Exception {

    public WithdrawalAmountTooLargeException(String message) {
        super(message);
    }
}
