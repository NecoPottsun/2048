package com.example.demo;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountDaoTest {

    private AccountDao accountDao = AccountDao.getInstance();
    private Account accountTest = new Account(3, "Beatrice",120000);
    private Account accountFind;

    @Test
    public void findByIdPass() {
        accountFind = new Account(2, "Duyen",100);
        Account account = accountDao.findById(2);
        assertTrue(account.getId() == accountFind.getId()
                        && account.getUsername().equals(accountFind.getUsername())
                        && account.getScore() == accountFind.getScore()
                    );
    }
    @Test
    public void findByIdFail() {
        accountFind = new Account(1, "Neco",1000);
        accountDao = AccountDao.getInstance();
        Account account = accountDao.findById(2);
        assertFalse("error in findById()",account.getId() == accountFind.getId()
                && account.getUsername().equals(accountFind.getUsername())
                && account.getScore() == accountFind.getScore()
        );

    }

    @Test
    public void addPass() {
        accountDao.add(accountTest);
        List<Account> accountList = accountDao.getAccounts();
        Account account = accountDao.findById(3);
        assertTrue(account.getId() == accountTest.getId() &&
                    account.getUsername().equals(accountTest.getUsername())&&
                    account.getScore() == accountTest.getScore());
    }


    @Test
    public void deletePass() {
        int initialListSize = accountDao.getAccounts().size();
        accountDao.delete(accountTest);
        assertEquals(initialListSize-1, accountDao.getAccounts().size());
    }
    @Test
    public void deleteFail() {
        accountTest =  new Account(4, "Neco-Neco",10000);
        int initialListSize = accountDao.getAccounts().size();
        accountDao.delete(accountTest);
        assertNotEquals("error in deleteFail()",initialListSize-1, accountDao.getAccounts().size());
    }
}