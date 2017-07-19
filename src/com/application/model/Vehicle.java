package com.application.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cts1 on 17/7/17.
 */
public class Vehicle {
    private VehicleType vehicleType;
    private List<VehicleProperty> vehicleProps;
    private double perKmFare = VehicleProperty.BASEFARE.apply();

    private Vehicle(VehicleBuilder vehicleBuilder){
        this.vehicleProps = vehicleBuilder.vehicleProperties;
        this.vehicleType = vehicleBuilder.vehicleType;
        setPerKmFare();
    }

    public List<VehicleProperty> getVehicleProps() {
        return vehicleProps;
    }

    public void setVehicleProps(List<VehicleProperty> vehicleProps) {
        this.vehicleProps = vehicleProps;
    }

    public void updatePassengerCount(int passengers){
        int passDiff = passengers-vehicleType.getCapacity();
        perKmFare = passDiff>0 ?(perKmFare+=(passDiff)): perKmFare;
    }


    public double getPerKmFare() {
        return perKmFare;
    }

    public void setPerKmFare(){
        for(VehicleProperty prop : vehicleProps){
            perKmFare += prop.apply();
        }
    }

    public VehicleType getVehicleType() {

        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public static class VehicleBuilder{
        private VehicleType vehicleType;
        private LinkedList<VehicleProperty> vehicleProperties =  new LinkedList<>();

        public VehicleBuilder(VehicleType vehicleType){
            this.vehicleType = vehicleType;
        }

        public VehicleBuilder addProperty(VehicleProperty vehicleProperty){
            this.vehicleProperties.add(vehicleProperty);
            return this;
        }

        public VehicleBuilder diesel(){
            this.vehicleProperties.add(VehicleProperty.DIESEL);
            return this;
        }

        public VehicleBuilder ac(){
            this.vehicleProperties.add(VehicleProperty.AC);
            return this;
        }


        public Vehicle build(){
            return new Vehicle(this);
        }
    }

}
