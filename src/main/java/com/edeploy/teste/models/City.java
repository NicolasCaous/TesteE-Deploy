package com.edeploy.teste.models;

public class City {

    private String name;
    private String state;
    private float score;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public City(String name, String state, float score) {
        this.name = name;
        this.state = state;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String city) {
        this.name = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
