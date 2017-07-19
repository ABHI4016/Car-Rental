package com.application.model;

import com.application.exception.NoConnectionFoundException;
import com.application.exception.RouteInCompleteException;

/**
 * Created by cts1 on 18/7/17.
 */
public class Route {
    City city;
    Route route = null;

    public Route(City city){
        this.city = city;
    }

    public Route(City city, Route route){
        this.city = city;
        this.route =  route;
    }

    public Route getRoute() {
        return route;
    }

    public City getCity() {

        return city;
    }

    public Double getDistance() throws RouteInCompleteException, NoConnectionFoundException {
        if(route!=null){
            return this.route.getDistance(city.cityName);
        }else{
            throw new RouteInCompleteException();
        }

    }

    private Double getDistance(String city)throws NoConnectionFoundException{
        if(route!=null){
            return route.getDistance(this.city.cityName) + this.city.getNeighbourDistance(city);
        }else{
            Double distance = this.city.getNeighbourDistance(city);
            return distance;
        }
    }

    public String printRoute(){
        if(this.route==null){
            return this.city.cityName.toUpperCase();
        }
        return route.printRoute() + "-" +  this.city.cityName.toUpperCase();

    }
}
