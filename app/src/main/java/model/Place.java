package model;

import org.w3c.dom.Text;

/**
 * Created by Soporte on 09/10/2016.
 */
public class Place {
    private int placeID;
    private String name;
    private Text description;
    private Text activity;
    private double price;

    public Place(int placeID, String name, Text description, Text activity, double price) {
        this.placeID = placeID;
        this.name = name;
        this.description = description;
        this.activity = activity;
        this.price = price;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Text getDescription() {
        return description;
    }

    public void setDescription(Text description) {
        this.description = description;
    }

    public Text getActivity() {
        return activity;
    }

    public void setActivity(Text activity) {
        this.activity = activity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
