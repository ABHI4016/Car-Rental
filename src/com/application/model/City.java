package com.application.model;

import com.application.exception.NoConnectionFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cts1 on 18/7/17.
 */
public class City {
    String cityName;
    Map<String, Double> neighbours;

    private City(CityBuilder cityBuilder){
        this.cityName = cityBuilder.name;
        this.neighbours = cityBuilder.neighbours;
    };

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Map<String, Double> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Map<String, Double> neighbours) {
        this.neighbours = neighbours;
    }

    public Double getNeighbourDistance(String city) throws NoConnectionFoundException{
        Double distance = neighbours.get(city.toLowerCase());
        if(distance==null){
            throw new NoConnectionFoundException();
        }else{
            return distance;
        }
    };

    public static class CityBuilder{
        String name;
        Map<String, Double> neighbours;

        public CityBuilder(String name){
            this.name = name;
            neighbours = new HashMap<>();
        }

        public CityBuilder addNeighbour(String city, Double distance){
            neighbours.put(city, distance);
            return this;
        }

        public City build(){
            return new City(this);
        }

    }

    public String toString(){
        return this.cityName.toUpperCase();
    }

    public String toStringWithNeighbours(){
        StringBuilder cityDescription = new StringBuilder(cityName.toUpperCase());
        for(String city : neighbours.keySet()){
            cityDescription.append( "\n" +  city.toUpperCase() + " (Distance: " + neighbours.get(city) + ")");
        }
        return cityDescription.toString();
    }
}
