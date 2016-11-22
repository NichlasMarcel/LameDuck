/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtu.MMMNGG;

import Fastmoney.AccountType;
import Fastmoney.BankPortType;
import Fastmoney.BankService;
import Fastmoney.CreditCardFaultMessage;
import Fastmoney.CreditCardInfoType;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author mtkx
 */
@WebService(serviceName = "MainWebService")
public class LameDuckWebService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;

    int groupNumber = 23;
    ArrayList<FlightInfoObject> listOfFO = new ArrayList<>();
    ArrayList<String> airlineList = new ArrayList<>(Arrays.asList("SAS","Thomas Cook","Norwegian", "Ryan Air", "Qatar Airway", "Emirates"));
    ArrayList<String> carrierList = new ArrayList<>(Arrays.asList("B. e. st. Carriers","WCY","Wild Carriers", "Carrier ApS"));
    ArrayList<String> startTime = new ArrayList<>(Arrays.asList("06:11", "08:34","10:12","11:38", "14:01", "15:34"));
    ArrayList<String> endTime = new ArrayList<>(Arrays.asList("16:25", "17:03", "17:45", "19:10", "20:18", "22:50"));
    ArrayList<Integer> costForFlight = new ArrayList<>(Arrays.asList(150, 225, 275, 540, 780, 1087, 1320, 1580, 1725));
    Random random = new Random();
    int millisInDay = 24*60*60*1000;
    
    ArrayList<FlightInfoObject> booked_flights = new ArrayList<FlightInfoObject>();
    
    public LameDuckWebService() throws ParseException {
        generateData();
        service = new BankService();
    }
    
    private void generateData() throws ParseException {
 
        Date CAnovemberDate = new SimpleDateFormat("dd/MM/yyyy").parse("10/11/2016");
        Date ACnovemberDate = new SimpleDateFormat("dd/MM/yyyy").parse("22/11/2016");
        
        Date ACdecemberDate = new SimpleDateFormat("dd/MM/yyyy").parse("15/12/2016");
        Date CAdecemberDate = new SimpleDateFormat("dd/MM/yyyy").parse("27/12/2016");
        
        //November flights
        listOfFO.add(new FlightInfoObject("CANOV215",215,"SAS",CAnovemberDate, new FlightObject("Copenhagen","Amsterdam", CAnovemberDate,"08:15",CAnovemberDate,"10:35","B.e.st. Carriers")));
        listOfFO.add(new FlightInfoObject("CANOV380",380,"Norwegian",CAnovemberDate, new FlightObject("Copenhagen","Amsterdam", CAnovemberDate,"11:45",CAnovemberDate,"14:05","Wind Carriers")));
        
        listOfFO.add(new FlightInfoObject("ACNOV999",999,"Thomas Cook",ACnovemberDate, new FlightObject("Amsterdam", "Copenhagen",ACnovemberDate,"14:20",ACnovemberDate,"16:00","B.Carriers Inc")));
        listOfFO.add(new FlightInfoObject("ACNOV50",50,"Amsterdam Airlines",ACnovemberDate, new FlightObject("Amsterdam", "Copenhagen", ACnovemberDate,"17:30",ACnovemberDate,"18:55","SomeCarriers ApS")));
        
        //December flights
        listOfFO.add(new FlightInfoObject("ACDEC150",150,"Amsterdam Airways",ACdecemberDate, new FlightObject("Amsterdam", "Copenhagen", ACdecemberDate,"11:05",ACdecemberDate,"15:55","Carriers of Amsterdam")));
        listOfFO.add(new FlightInfoObject("ACDEC260",260,"Qatar",ACdecemberDate, new FlightObject("Amsterdam", "Copenhagen", ACdecemberDate,"11:45",ACdecemberDate,"14:05","Qatar Carriers")));
        
        listOfFO.add(new FlightInfoObject("CADEC700",700,"Copenhagen Airlines",CAdecemberDate, new FlightObject("Copenhagen","Amsterdam",CAdecemberDate,"15:10",CAdecemberDate,"16:05","Copenhagen Carirers")));
        listOfFO.add(new FlightInfoObject("CADEC250",250,"RyanAir",CAdecemberDate, new FlightObject("Copenhagen","Amsterdam", CAdecemberDate,"22:30",CAdecemberDate,"23:20","Wild Carriers Inc")));
        
    }
    
    @WebMethod(operationName = "getFlights")
    public FlightInfoObjectWrapper getFlights(@WebParam(name = "from") String fromDestination, @WebParam(name = "toDestination") String toDestination, Date travelDate) {
        
        boolean flightsExistForDate = false;
        
        ArrayList<FlightInfoObject> flightCollection = new ArrayList<>();
        
        for (int i = 0; i < listOfFO.size(); i++) 
        {
            if(listOfFO.get(i).getFO().getStartDate().equals(travelDate) && listOfFO.get(i).getFO().getStartAirport().equals(fromDestination) && listOfFO.get(i).getFO().getDestinationAirport().equals(toDestination)) {
                
                flightCollection.add(listOfFO.get(i));
                flightsExistForDate = true;
            }
        }       
        
        return new FlightInfoObjectWrapper(flightCollection);
    }
    
    @WebMethod(operationName = "bookFlights")
    public boolean bookFlights(@WebParam(name = "bookingNumber") String bookingNumber, @WebParam(name = "creditCard") CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        
        boolean flightExist = false;
        
        FlightInfoObject tmpfio = null;
        //First, check if flight exist
        for (int i = 0; i < listOfFO.size(); i++) {
            
            if(listOfFO.get(i).getBookingNumber().equals(bookingNumber)) {
                
                tmpfio = listOfFO.get(i);
                
                flightExist = true;
                break;
            }
            
        }
        
        //If the booking number exist, validate creditcard
        if(flightExist == true) {
            
            boolean isCCvalid = validateCreditCard(groupNumber, creditCardInfo, tmpfio.getPrice());
            
            //Credit card is valid
            if (isCCvalid) {
                
                AccountType account = new AccountType();        
                account.setName("LameDuck");
                account.setNumber("50208812");
                
                if(chargeCreditCard(groupNumber, creditCardInfo, tmpfio.getPrice(), account)){
                    booked_flights.add(tmpfio);
                    return true;
                }
                
                return false;
            }
            //Credit card is invalid or does not have enough money
            else {
                return false;
            }
            

        }
        //If the booking number doesn't exist, return false
        else {
            return flightExist;
        }
        
        
    }
    
    @WebMethod(operationName = "cancelFlights")
    public boolean cancelFlights(@WebParam(name = "bookingnumber") String bookingnumber,@WebParam(name = "amount") int amount, @WebParam(name = "creditCard") CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        int amountToRefund = amount / 2;
        
        AccountType account = new AccountType();        
        account.setName("LameDuck");
        account.setNumber("50208812");
             
        for(FlightInfoObject bookFlight : booked_flights){
            if(bookFlight.getBookingNumber().equals(bookingnumber))
            {
                if(refundCreditCard(groupNumber, creditCardInfo, amountToRefund, account)){
                    booked_flights.remove(bookFlight);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    
    @WebMethod(operationName = "WorkAround")
    public FlightInfoObject test(@WebParam(name = "flight") FlightInfoObject flight) throws CreditCardFaultMessage {
        return  flight;
    }
    
    /*private FlightInfoObject generateNewFlightInfoObject(String from, String to, Date date) {
    
        //Time startTime = new Time((long)random.nextInt(millisInDay));
        //Time endTime = new Time((long)random.nextInt(millisInDay));
        //Date endDate;
        
        int startRandomInt = random.nextInt(startTime.size());
        String takeOffTime = startTime.get(startRandomInt);
        
        int endRandomInt = random.nextInt(endTime.size());
        String landingTime = endTime.get(endRandomInt);
        
        int carrierRandomInt = random.nextInt(carrierList.size());
        String carrier = carrierList.get(carrierRandomInt);
     
        FlightObject flight = new FlightObject(from, to, date, takeOffTime, date, landingTime, carrier);
        
        String bookingNumber = generateBookingNumber();
        
        int costRandomInt = random.nextInt(costForFlight.size());
        int flightCost = costForFlight.get(costRandomInt);
        
        int airlineRandomInt = random.nextInt(airlineList.size());
        String airlineOperator = airlineList.get(airlineRandomInt);
        
        
        FlightInfoObject fio = new FlightInfoObject(bookingNumber, flightCost, airlineOperator, date, flight);
        
        return fio;
    }
    
    private String generateBookingNumber() {
        String bookingRange = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder tmp = new StringBuilder();
        Random random = new Random();
        while (tmp.length() < 10) {
            int index = (int) (random.nextInt() * bookingRange.length());
            tmp.append(bookingRange.charAt(index));
        }
        String bookingNumber = tmp.toString();
        return bookingNumber;

    }*/

    private boolean chargeCreditCard(int group, Fastmoney.CreditCardInfoType creditCardInfo, int amount, Fastmoney.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, Fastmoney.CreditCardInfoType creditCardInfo, int amount, Fastmoney.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean validateCreditCard(int group, Fastmoney.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }
}
