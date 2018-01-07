package com.edeploy.teste.services;

import java.util.HashMap;
import java.util.Map;

import com.edeploy.teste.configurations.URLs;
import com.edeploy.teste.models.City;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetCityScore {

    private City city;

    public GetCityScore(City city) {
        this.city = city;
    }

    public float run() throws UnirestException {
        Map<String, String> mapJson = new HashMap<String, String>();

        mapJson.put("Nome", this.city.getName());
        mapJson.put("Estado", this.city.getState());

        HttpResponse<String> response = Unirest.post(
            URLs.BUSCA_PONTOS
        ).body(
            new Gson().toJson(mapJson)
        ).asString();

        return Float.parseFloat(response.getBody());
    }
}
