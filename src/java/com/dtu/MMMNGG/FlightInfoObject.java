/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtu.MMMNGG;

import java.util.Date;


/**
 *
 * @author mtkx
 */

public class FlightInfoObject {
    
    private String bookingNumber;
    private int price;
    private String nameOfAirline;
    private Date dateOfTravel;
    private FlightObject FO;
    
    public FlightInfoObject(String booking, int cost, String airline, Date date, FlightObject fo) {
        
        bookingNumber = booking;
        price = cost;
        nameOfAirline = airline;
        dateOfTravel = date;
        FO = fo;
    }

    /**
     * @return the bookingNumber
     */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /**
     * @param bookingNumber the bookingNumber to set
     */
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the nameOfAirline
     */
    public String getNameOfAirline() {
        return nameOfAirline;
    }

    /**
     * @param nameOfAirline the nameOfAirline to set
     */
    public void setNameOfAirline(String nameOfAirline) {
        this.nameOfAirline = nameOfAirline;
    }

    /**
     * @return the dateOfTravel
     */
    public Date getDateOfTravel() {
        return dateOfTravel;
    }

    /**
     * @param dateOfTravel the dateOfTravel to set
     */
    public void setDateOfTravel(Date dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    /**
     * @return the FO
     */
    public FlightObject getFO() {
        return FO;
    }

    /**
     * @param FO the FO to set
     */
    public void setFO(FlightObject FO) {
        this.FO = FO;
    }
}
