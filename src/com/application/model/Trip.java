package com.application.model;

import com.application.exception.RouteInCompleteException;

import java.util.LinkedList;
import java.util.List;

public class Trip{

    private Vehicle vehicle;
    private Route route;
    private int passengers;
    private List<TripProps> tripProps = new LinkedList<>();
    private double fare;

    private Trip(TripBuilder tripBuilder) throws RouteInCompleteException {
        setVehicle(tripBuilder.vehicle);
        setTripProps(tripBuilder.tripProps);
        setRoute(tripBuilder.route);
        setFare();
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<TripProps> getTripProps() {
        return tripProps;
    }

    public void setTripProps(List<TripProps> tripProps) {
        this.tripProps = tripProps;
    }

    public List<TripProps> getTripType() {
        return tripProps;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void setFare() throws RouteInCompleteException {
        this.fare = route.getDistance() * vehicle.getPerKmFare();
        if(tripProps!=null){
           for(TripProps props : tripProps){
               props.apply(fare);
            }
        }
        fare = vehicle.getVehicleType().equals(VehicleType.Bus)?fare*=.98:fare;
    }

    public boolean isBus(){
        return this.vehicle.getVehicleType().equals(VehicleType.Bus);
    }

    public double getFare(){
        return fare;
    }

    public static class TripBuilder{
        private Vehicle vehicle;
        private Route route;
        private List<TripProps> tripProps = null;
        private int passengers;

        public TripBuilder(Vehicle vehicle, Route route){
            this.vehicle = vehicle;
            this.route = route;
            passengers = this.vehicle.getVehicleType().getCapacity();
        }

        public TripBuilder passengers(int passengers){
            this.passengers = passengers;
            this.vehicle.updatePassengerCount(passengers);
            return this;
        }

        public TripBuilder addProperty(TripProps tripProps){
            this.tripProps.add(tripProps);
            return this;
        }

        public Trip build() throws RouteInCompleteException {
            return new Trip(this);
        }
    }
}
