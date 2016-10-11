package com.gotravel.mobile.models;

/**
 * Created by RGodoy on 08/10/2016.
 */

public class Airline {

    private int airlineId;
    private String name;
    private String description;

    public Airline(int airlineId, String name, String description) {
        this.airlineId=airlineId;
        this.name = name;
        this.description = description;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

