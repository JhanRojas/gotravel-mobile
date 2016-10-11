package com.gotravel.mobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gotravel.mobile.R;
import com.gotravel.mobile.activities.HotelDetailActivity;
import com.gotravel.mobile.activities.RoomDetailActivity;
import com.gotravel.mobile.models.Room;

import java.util.List;

/**
 * Created by jhanrojas on 10/10/16.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private final List<Room> roomList;
    private final int hotelId;
    private final String hotelName;

    public RoomAdapter(List<Room> roomList, int hotelId, String hotelName){
        this.roomList = roomList;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GOTRAVEL","RoomViewHolder onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RoomViewHolder holder, int position) {
        Log.d("GOTRAVEL","RoomViewHolder onBindViewHolder");
        holder.room = roomList.get(position);
        holder.roomNameTextView.setText(roomList.get(position).name);
        holder.roomPriceTextView.setText("$ " + roomList.get(position).price.toString());
        holder.roomNumberPeopleTextView.setText(roomList.get(position).numberPeople + " people");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GOTRAVEL","RoomViewHolder onClick");
                Intent intent = new Intent(v.getContext(), RoomDetailActivity.class);
                intent.putExtra(RoomDetailActivity.PARAM_ID, holder.room.id);
                intent.putExtra(RoomDetailActivity.PARAM_NAME, holder.room.name);
                intent.putExtra(RoomDetailActivity.PARAM_TYPE, holder.room.roomType);
                intent.putExtra(RoomDetailActivity.PARAM_NUMBER_PEOPLE, holder.room.numberPeople);
                intent.putExtra(RoomDetailActivity.PARAM_PRICE, holder.room.price);
                intent.putExtra(RoomDetailActivity.PARAM_DESC, holder.room.description);
                intent.putExtra(RoomDetailActivity.PARAM_HOTEL_ID, holder.room.hotelId);
                intent.putExtra(RoomDetailActivity.PARAM_HOTEL_NAME, hotelName);
                v.getContext().startActivity(intent);
                //v.getContext().startActivity(new Intent(v.getContext(), RoomDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList != null ? roomList.size() : 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView roomNameTextView;
        public final TextView roomNumberPeopleTextView;
        public final TextView roomPriceTextView;
        public Room room;

        public RoomViewHolder(View view) {
            super(view);
            mView = view;
            roomNameTextView = (TextView) view.findViewById(R.id.roomNameTextView);
            roomNumberPeopleTextView = (TextView) view.findViewById(R.id.roomNumberPeopleTextView);
            roomPriceTextView = (TextView) view.findViewById(R.id.roomPriceTextView);
        }
    }
}
