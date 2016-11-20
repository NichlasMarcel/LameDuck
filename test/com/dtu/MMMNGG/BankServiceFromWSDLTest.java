/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtu.MMMNGG;

import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mtkx
 */
public class BankServiceFromWSDLTest {
    
    public BankServiceFromWSDLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of chargeCreditCard method, of class BankServiceFromWSDL.
     */
    @Test
    public void testChargeCreditCard() throws Exception {
        System.out.println("chargeCreditCard");
        int group = 23;
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        creditCardInfo.setName("Bech Camilla");
        creditCardInfo.setNumber("50408822");
        
        ExpirationDate xp = new ExpirationDate();
        xp.setMonth(7);
        xp.setYear(9);
        creditCardInfo.setExpirationDate(xp);
        
        int amount = 100;
        
        AccountType account = new AccountType();        
        account.setName("LameDuck");
        account.setNumber("50208812");
        
        BankServiceFromWSDL instance = new BankServiceFromWSDL();
        
        boolean expResult = true;
        boolean result = instance.chargeCreditCard(group, creditCardInfo, amount, account);
        
        System.out.println("Result: " + result);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of refundCreditCard method, of class BankServiceFromWSDL.
     */
    @Test
    public void testRefundCreditCard() throws Exception {
        System.out.println("refundCreditCard");
        int group = 23;
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        creditCardInfo.setName("Bech Camilla");
        creditCardInfo.setNumber("50408822");
        
        ExpirationDate xp = new ExpirationDate();
        xp.setMonth(7);
        xp.setYear(9);
        creditCardInfo.setExpirationDate(xp);
        
        int amount = 100;
        
        AccountType account = new AccountType();        
        account.setName("LameDuck");
        account.setNumber("50208812");
        
        BankServiceFromWSDL instance = new BankServiceFromWSDL();
        boolean expResult = true;
        boolean result = instance.refundCreditCard(group, creditCardInfo, amount, account);
        
        System.out.println("Result: " + result);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of validateCreditCard method, of class BankServiceFromWSDL.
     */
    @Test
    public void testValidateCreditCard() throws Exception {
        System.out.println("validateCreditCard");
        int group = 23;
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        creditCardInfo.setName("Bech Camilla");
        creditCardInfo.setNumber("50408822");
        
        ExpirationDate xp = new ExpirationDate();
        xp.setMonth(7);
        xp.setYear(9);
        
        creditCardInfo.setExpirationDate(xp);
        int amount = 50;
        BankServiceFromWSDL instance = new BankServiceFromWSDL();
        boolean expResult = true;
        boolean result = instance.validateCreditCard(group, creditCardInfo, amount);
        
        System.out.println("Result: " + result);
        
        assertEquals(expResult, result);
    }
    
}
