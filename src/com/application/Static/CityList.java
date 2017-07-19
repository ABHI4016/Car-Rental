package com.application.Static;

import com.application.exception.CityNotFoundException;
import com.application.model.City;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cts1 on 18/7/17.
 */
public class CityList {
    private static City pune;
    private static City mumbai;
    private static City banglore;
    private static City chennai;

    static Map<String, City> cities =  new HashMap<>();

    public CityList(){
        pune = new City.CityBuilder("pune")
                .addNeighbour("banglore", 900.0)
                .addNeighbour("chennai", 1200.0)
                .addNeighbour("mumbai", 200.0)
                .build();
        mumbai = new City.CityBuilder("mumbai")
                .addNeighbour("banglore", 1100.0)
                .addNeighbour("chennai", 1400.0)
                .addNeighbour("pune", 200.0)
                .build();
        banglore = new City.CityBuilder("banglore")
                .addNeighbour("pune", 900.0)
                .addNeighbour("chennai", 300.0)
                .addNeighbour("mumbai", 1100.0)
                .build();
        chennai = new City.CityBuilder("chennai")
                .addNeighbour("pune", 1200.0)
                .addNeighbour("banglore", 300.0)
                .addNeighbour("mumbai", 1400.0)
                .build();

        cities.put("mumbai", mumbai);
        cities.put("pune", pune);
        cities.put("chennai", chennai);
        cities.put("banglore", banglore);
    }

    public static Map<String, City> getCities() {
        return cities;
    }

    public static void addCity(City city) {
        cities.put(city.getCityName().toLowerCase(),city);
    }

    public City getCity(String city) throws CityNotFoundException {
        City cityObj = cities.get(city.toLowerCase());
        if(cityObj==null){
            throw new CityNotFoundException();
        }
        return cityObj;
    }
}
