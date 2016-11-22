/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtu.MMMNGG;

import java.util.ArrayList;


/**
 *
 * @author Nichlas
 */
public class FlightInfoObjectWrapper {
    ArrayList<FlightInfoObject> list = new ArrayList<>();
    
    public FlightInfoObjectWrapper(ArrayList<FlightInfoObject> list) {
        this.list = list;
    }
    
    public ArrayList<FlightInfoObject> getList() {
        return list;
    }
    
}
