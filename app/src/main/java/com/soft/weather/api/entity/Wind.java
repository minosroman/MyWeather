package com.soft.weather.api.entity;

/**
 * Created by minos on 12.01.2017.
 */

public class Wind {
    private double speed;
    private double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return this.deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}