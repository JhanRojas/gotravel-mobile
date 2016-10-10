package com.gotravel.mobile.models;

/**
 * Created by jhanrojas on 9/10/16.
 */

public class Hotel {
    public final String id;
    public final String name;
    public final String description;
    public final String pictureUrl;

    public Hotel(String id, String name, String description, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

}
