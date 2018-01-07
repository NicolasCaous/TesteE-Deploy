package com.edeploy.teste.controllers;

import java.util.List;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edeploy.teste.models.City;
import com.edeploy.teste.services.GetCityScore;
import com.edeploy.teste.services.QueryCityOrState;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/cities", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> query(@RequestParam("cityQuery") String cityQuery, @RequestParam("stateQuery") String stateQuery) {
        List<City> cities = null;
        try {
            cities = new QueryCityOrState(cityQuery, stateQuery).run();
        } catch (UnirestException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(new Gson().toJson(cities));
    }

    @RequestMapping(value = "/cities/score", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> queryCity(@RequestParam("name") String name, @RequestParam("state") String state) {
        Float score = null;
        try {
            score = new GetCityScore(new City(name, state)).run();
        } catch (UnirestException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
        return ResponseEntity.ok(new Gson().toJson(score));
    }
}
