package com.soft.weather.api.entity;

/**
 * Created by minos on 12.01.2017.
 */

public class Clouds {
    private int all;

    public Clouds(int all) {
        this.all = all;
    }

    public int getAll() {
        return this.all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}