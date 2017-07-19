package com.application.model;

/**
 * Created by cts1 on 17/7/17.
 */
public enum VehicleType {
    Bus(20), SUV(8), CAR(4), Other(5), SWIFT(4);

    private int value;

    VehicleType(int value){
        this.value = value;
    }

    public int getCapacity(){
        return this.value;
    }
}
