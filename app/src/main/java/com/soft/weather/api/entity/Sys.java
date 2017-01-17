package com.soft.weather.api.entity;

/**
 * Created by minos on 12.01.2017.
 */

public class Sys {
    private int type;
    private int id;
    private double message;
    private String country;
    private int sunrise;
    private int sunset;

    public Sys(int type, int id, double message, String country, int sunrise, int sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return this.message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return this.sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}