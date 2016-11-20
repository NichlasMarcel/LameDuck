/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtu.MMMNGG;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author mtkx
 */
public class FlightObject {
    
    private String startAirport;
    private String destinationAirport;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private String carrier;
    
    public FlightObject(String startAirPort, String destinationAirPort, Date startdate, String starttime, Date enddate, String endtime, String carrierOfFlight) {
        
        startAirport = startAirPort;
        destinationAirport = destinationAirPort;
        startDate = startdate;
        startTime = starttime;
        endDate = enddate;
        endTime = endtime;
        carrier = carrierOfFlight;      
        
    }

    /**
     * @return the startAirport
     */
    public String getStartAirport() {
        return startAirport;
    }

    /**
     * @param startAirport the startAirport to set
     */
    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    /**
     * @return the destinationAirport
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * @param destinationAirport the destinationAirport to set
     */
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the carrier
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * @param carrier the carrier to set
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
