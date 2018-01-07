package com.edeploy.teste.services;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;

import com.edeploy.teste.models.City;
import com.mashape.unirest.http.exceptions.UnirestException;

public class QueryCityOrState {

    private String cityQuery;
    private String stateQuery;

    public QueryCityOrState(String cityQuery, String stateQuery) {
        this.cityQuery = cityQuery;
        this.stateQuery = stateQuery;
    }

    public List<City> run() throws UnirestException, JSONException {
        List<City> cities = new GetAllCities().run();

        for (Iterator<City> iterator = cities.iterator(); iterator.hasNext(); ) {
            City city = iterator.next();
            if(!city.getName().toLowerCase().contains(this.cityQuery.toLowerCase())
            && !city.getState().toLowerCase().contains(this.stateQuery.toLowerCase())) {
                iterator.remove();
            }
        }

        return cities;
    }
}
