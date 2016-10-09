package model;

import java.util.Date;

/**
 * Created by RGodoy on 08/10/2016.
 */

public class Flight {
    private String   originCountry;
    private String   originState;
    private  String originCity;
    private  String destinationCountry;
    private  String destinationState;
    private  String destinationCity;
    private  Date  departure;
    private Date returning;
    private double adultPrice;
    private double  minorPrice;
    private int  capacity;
    private Airline airline;

    public Flight(String originCountry, String originState, String originCity, String destinationCountry, String destinationState, String destinationCity, Date departure, Date returning, double adultPrice, double minorPrice, int capacity, Airline airline) {
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
        this.airline = airline;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginState() {
        return originState;
    }

    public void setOriginState(String originState) {
        this.originState = originState;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getReturning() {
        return returning;
    }

    public void setReturning(Date returning) {
        this.returning = returning;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getMinorPrice() {
        return minorPrice;
    }

    public void setMinorPrice(double minorPrice) {
        this.minorPrice = minorPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
