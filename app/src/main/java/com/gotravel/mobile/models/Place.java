package com.gotravel.mobile.models;

/**
 * Created by Soporte on 11/10/2016.
 */

public class Place {
    public final int id;
    public final String name;
    public final String description;
    public final String activity;
    public final Double price;


    public Place (int id, String name, String description, String activity, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.activity = activity;
        this.price = price;

    }
}
