package com.gotravel.mobile.models;

/**
 * Created by jhanrojas on 10/10/16.
 */

public class Room {
    public final int id;
    public final String name;
    public final String roomType;
    public final String description;
    public final int numberPeople;
    public final Double price;
    public final int hotelId;

    public Room (int id, String name, String roomType, int numberPeople, Double price, String description, int hotelId) {
        this.id = id;
        this.roomType = roomType;
        this.name = name;
        this.description = description;
        this.numberPeople = numberPeople;
        this.price = price;
        this.hotelId = hotelId;
    }
}
