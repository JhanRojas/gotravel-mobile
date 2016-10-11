package com.gotravel.mobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gotravel.mobile.R;
import com.gotravel.mobile.models.Place;
import com.gotravel.mobile.util.Constants;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String PARAM_ID = "place_id";
    public static final String PARAM_NAME = "place_name";
    public static final String PARAM_DESCRIPTION = "place_description";
    public static final String PARAM_ACTIVITY = "place_activity";
    public static final String PARAM_PRICE = "place_price";



    private TextView placeNameTextView;
    private TextView placeDescriptionTextView;
    private TextView placeActivityTextView;
    private TextView placePriceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        Place place = new Place(
                getIntent().getIntExtra(PARAM_ID,0),
                getIntent().getStringExtra(PARAM_NAME),
                getIntent().getStringExtra(PARAM_DESCRIPTION),
                getIntent().getStringExtra(PARAM_ACTIVITY),
                getIntent().getDoubleExtra(PARAM_PRICE,0));



        placeNameTextView = (TextView) findViewById(R.id.placeNameTextView);
        placeDescriptionTextView = (TextView) findViewById(R.id.placeDescriptionTextView);
        placeActivityTextView = (TextView) findViewById(R.id.placeActivityTextView);
        placePriceTextView = (TextView) findViewById(R.id.placePriceTextView);



        placeNameTextView.setText(place.name);
        placeDescriptionTextView.setText(place.description);
        placeActivityTextView.setText(place.activity+" people");
        placePriceTextView.setText(place.price.toString());


    }
}
