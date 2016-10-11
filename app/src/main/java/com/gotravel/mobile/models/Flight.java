package com.gotravel.mobile.models;

import java.util.Date;

/**
 * Created by RGodoy on 08/10/2016.
 */

public class Flight {
    public final int id;
    public final String   originCountry;
    public final String   originState;
    public final  String originCity;
    public final  String destinationCountry;
    public final  String destinationState;
    public final  String destinationCity;
    public final  String  departure;
    public final String returning;
    public final double adultPrice;
    public final double  minorPrice;
    public final int  capacity;
    public final int airline_id;
    public final String pictureUrl;

    public Flight(int id, String originCountry, String originState, String originCity, String destinationCountry, String destinationState, String destinationCity, String departure, String returning, double adultPrice, double minorPrice, int capacity, int airline_id, String pictureUrl) {
        this.id = id;
        this.originCountry = originCountry;
        this.originState = originState;
        this.originCity = originCity;
        this.destinationCountry = destinationCountry;
        this.destinationState = destinationState;
        this.destinationCity = destinationCity;
        this.departure = departure;
        this.returning = returning;
        this.adultPrice = adultPrice;
        this.minorPrice = minorPrice;
        this.capacity = capacity;
        this.airline_id = airline_id;
        this.pictureUrl = pictureUrl;
    }
}
