package com.elevate.test01.luntuncwadi.exceptions;

/**
 * A custom exception for a non existent account
 */
public class AccountNotFoundException  extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
