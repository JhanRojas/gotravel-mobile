package com.gotravel.mobile.models;

/**
 * Created by jhanrojas on 9/10/16.
 */

public class Hotel {
    public final int id;
    public final String name;
    public final String description;
    public final String pictureUrl;

    public Hotel(int id, String name, String description, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

}
