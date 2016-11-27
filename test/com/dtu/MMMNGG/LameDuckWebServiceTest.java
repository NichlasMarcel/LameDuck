/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtu.MMMNGG;

import Fastmoney.CreditCardInfoType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class LameDuckWebServiceTest {
    
    public LameDuckWebServiceTest() {
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
     * Test of getFlights method, of class LameDuckWebService.
     */
    @Test
    public void testGetFlights() throws Exception {
        System.out.println("getFlights");
        String fromDestination = "Copenhagen";
        String toDestination = "Amsterdam";
        Date travelDate = new SimpleDateFormat("dd/MM/yyyy").parse("10/11/2016");
        System.out.println("Date: " + travelDate.toString());
        
        LameDuckWebService instance = new LameDuckWebService();
        //ArrayList<FlightInfoObject> expResult = null;
        int expResult = 2;
        List<FlightInfoObject> result = instance.getFlights(fromDestination, toDestination, travelDate);
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of bookFlights method, of class LameDuckWebService.
     */
    @Test
    public void testBookFlights() throws Exception {
        System.out.println("bookFlights");
        //Valid booking number
        String bookingNumber = "CANOV215";
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        creditCardInfo.setName("Bech Camilla");
        creditCardInfo.setNumber("50408822");
        
        CreditCardInfoType.ExpirationDate xp = new CreditCardInfoType.ExpirationDate();
        xp.setMonth(7);
        xp.setYear(9);
        creditCardInfo.setExpirationDate(xp);
        
        
        
        LameDuckWebService instance = new LameDuckWebService();
        boolean expResult = true;
        boolean result = instance.bookFlights(bookingNumber, creditCardInfo);
        
        System.out.println("bookFlights value returned:" + result + ". Expected: " + expResult);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testBookFlights2() throws Exception {
        System.out.println("bookFlights");
        String bookingNumber = "NonExistingBookingNumber";
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        creditCardInfo.setName("Bech Camilla");
        creditCardInfo.setNumber("50408822");
        
        CreditCardInfoType.ExpirationDate xp = new CreditCardInfoType.ExpirationDate();
        xp.setMonth(7);
        xp.setYear(9);
        creditCardInfo.setExpirationDate(xp);
        
        
        
        LameDuckWebService instance = new LameDuckWebService();
        boolean expResult = false;
        boolean result = instance.bookFlights(bookingNumber, creditCardInfo);
        
        System.out.println("bookFlights value returned:" + result + ". Expected: " + expResult);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of cancelFlights method, of class LameDuckWebService.
     */
    @Test
    public void testCancelFlights() throws Exception {
        System.out.println("cancelFlights");
        String bookingnumber = "ABC";
        int amount = 100;
        CreditCardInfoType creditCardInfo = new CreditCardInfoType();
        creditCardInfo.setName("Bech Camilla");
        creditCardInfo.setNumber("50408822");
        
        CreditCardInfoType.ExpirationDate xp = new CreditCardInfoType.ExpirationDate();
        xp.setMonth(7);
        xp.setYear(9);
        creditCardInfo.setExpirationDate(xp);
        
        LameDuckWebService instance = new LameDuckWebService();
        boolean expResult = true;
        boolean result = instance.cancelFlights(bookingnumber, amount, creditCardInfo);
        
        System.out.println("cancelFlights value returned: " + result + ". Expected: " + expResult);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hello method, of class LameDuckWebService.
     */
    
    
}
