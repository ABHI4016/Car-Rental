package com.application.model;

/**
 * Created by cts1 on 17/7/17.
 */
public enum VehicleProperty {
    DIESEL(-1.0), PETROL(0.0), AC(2), BASEFARE(15.0);

    private double value;

    private VehicleProperty(double value){
        this.value=value;
    }

    public double apply(){
        return this.value;
    }
}
