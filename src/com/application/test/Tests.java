package com.application.test;

import com.application.Static.CityList;
import com.application.exception.CityNotFoundException;
import com.application.exception.RouteInCompleteException;
import com.application.model.*;
import com.application.resource.Services;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cts1 on 18/7/17.
 */
public class Tests {
    private Vehicle acBus;
    private Vehicle acOther;
    private Vehicle nonAcBus;
    private Vehicle nonAcOther;
    private CityList cityList = new CityList();
    private Route route;
    private Trip trip;
    private Services service = new Services();

    @BeforeTest
    public void setup(){
        this.acBus = new Vehicle.VehicleBuilder(VehicleType.Bus).ac().diesel().build();
        this.acOther = new Vehicle.VehicleBuilder(VehicleType.Other).diesel().ac().build();
        this.nonAcBus = new Vehicle.VehicleBuilder(VehicleType.Bus).diesel().build();
        this.nonAcOther = new Vehicle.VehicleBuilder(VehicleType.Other).build();

    }

    @Test
    public void testVehicleConfiguration(){
        assert acBus.getVehicleProps().contains(VehicleProperty.AC);
        assert acBus.getVehicleProps().contains(VehicleProperty.DIESEL);
        assert acBus.getVehicleType().equals(VehicleType.Bus);
        assert acBus.getPerKmFare() == 19.0;

    }

    @Test
    public void testRoute() throws CityNotFoundException, RouteInCompleteException {
        List<String> cities = new LinkedList<>(Arrays.asList("Pune","Mumbai","Chennai","Pune"));
        route = new Route(cityList.getCity(cities.get(0)));
        Boolean flag = true;
        for(String city : cities){
            if(flag){
                flag = false;
            }else{
                route = new Route(cityList.getCity(city), route);
            }
        }
        assert route.printRoute().equalsIgnoreCase("PUNE-MUMBAI-CHENNAI-PUNE");
        assert route.getDistance() == 2800.0;
    }

    @Test
    public void testTrip() throws RouteInCompleteException {
        trip = new Trip.TripBuilder(acBus, route).build();
        assert service.getFare(this.trip) == 2800 * 16 * 0.98;
    }

    @Test
    public void testTripWithExtraPax() throws RouteInCompleteException {
        trip = new Trip.TripBuilder(acBus, route).passengers(23).build();
        assert service.getFare(this.trip) == 2800 * 19 * 0.98;
    }

    @Test
    public void testCityConfiguration() throws CityNotFoundException{

        assert cityList.getCity("Pune").toStringWithNeighbours()
                        .equals("PUNE\n" +
                                    "MUMBAI (Distance: 200.0)\n" +
                                    "CHENNAI (Distance: 1200.0)\n" +
                                    "BANGLORE (Distance: 900.0)"
                        );

        assert cityList.getCity("Pune").toString().equals("PUNE");

    }

}
