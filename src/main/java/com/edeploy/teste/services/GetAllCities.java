package com.edeploy.teste.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.edeploy.teste.configurations.URLs;
import com.edeploy.teste.models.City;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetAllCities {

    public GetAllCities() {
        
    }

    public List<City> run() throws UnirestException, JSONException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(
            URLs.BUSCA_TODAS_CIDADES
        ).asJson();

        JSONArray jsonCities = jsonResponse.getBody().getArray();
        List<City> cities = new ArrayList<City>();

        for(int i=0; i<jsonCities.length(); ++i) {
            JSONObject city = jsonCities.getJSONObject(i);
            cities.add(new City(city.getString("Nome"), city.getString("Estado")));
        }

        return cities;
    }
}
