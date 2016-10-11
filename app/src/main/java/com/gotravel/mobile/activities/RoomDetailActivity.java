package com.gotravel.mobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gotravel.mobile.R;
import com.gotravel.mobile.models.Room;
import com.gotravel.mobile.util.Constants;

public class RoomDetailActivity extends AppCompatActivity {

    public static final String PARAM_ID = "room_id";
    public static final String PARAM_NAME = "room_name";
    public static final String PARAM_TYPE = "room_type";
    public static final String PARAM_NUMBER_PEOPLE = "room_number_people";
    public static final String PARAM_PRICE = "room_price";
    public static final String PARAM_DESC = "room_description";
    public static final String PARAM_HOTEL_ID = "room_hotel_id";
    public static final String PARAM_HOTEL_NAME = "room_hotel_name";

    private LinearLayout hotelHolder;
    private TextView hotelNameTextView;
    private TextView roomNameTextView;
    private TextView roomTypeTextView;
    private TextView roomNumberPeopleTextView;
    private TextView roomPriceTextView;
    private TextView roomDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        Room room = new Room(
                getIntent().getIntExtra(PARAM_ID,0),
                getIntent().getStringExtra(PARAM_NAME),
                getIntent().getStringExtra(PARAM_TYPE),
                getIntent().getIntExtra(PARAM_NUMBER_PEOPLE,0),
                getIntent().getDoubleExtra(PARAM_PRICE,0),
                getIntent().getStringExtra(PARAM_DESC),
                getIntent().getIntExtra(PARAM_HOTEL_ID,0));

        hotelHolder = (LinearLayout) findViewById(R.id.hotelHolder);
        hotelNameTextView = (TextView) findViewById(R.id.hotelNameTextView);
        roomNameTextView = (TextView) findViewById(R.id.roomNameTextView);
        roomTypeTextView = (TextView) findViewById(R.id.roomTypeTextView);
        roomNumberPeopleTextView = (TextView) findViewById(R.id.roomNumberPeopleTextView);
        roomPriceTextView = (TextView) findViewById(R.id.roomPriceTextView);
        roomDescriptionTextView = (TextView) findViewById(R.id.roomDescriptionTextView);

        hotelHolder.setBackgroundColor(Constants.HOLDER_BACKGROUND_COLOR);
        hotelNameTextView.setText(getIntent().getStringExtra(PARAM_HOTEL_NAME));
        roomNameTextView.setText(room.name);
        roomTypeTextView.setText(room.roomType);
        roomNumberPeopleTextView.setText(room.numberPeople+" people");
        roomPriceTextView.setText(room.price.toString());
        roomDescriptionTextView.setText(room.description);

    }
}
