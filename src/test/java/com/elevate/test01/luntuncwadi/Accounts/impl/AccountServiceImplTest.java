//package com.elevate.test01.luntuncwadi.Accounts.impl;
//
//import com.elevate.test01.luntuncwadi.Accounts.models.CurrentAccount;
//import com.elevate.test01.luntuncwadi.database.SystemDB;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
///**
// * A demonstration of unit testing.
// */
//
//class AccountServiceImplTest {
//   private SystemDB dbConnection = SystemDB.getInstance();
//   @Test
//    void  depositTest(){
//       AccountServiceImpl currentAccountService = new AccountServiceImpl();
//       CurrentAccount currentAccount = new CurrentAccount(1L, 1L, 0, 0);
//      dbConnection.addCurrentAccount(currentAccount);
//
//      currentAccountService.deposit(1L,200);
//       int results = currentAccount.getBalance();
//
//       Assertions.assertEquals(200,results);
//   }
////  TO DO MORE
//}