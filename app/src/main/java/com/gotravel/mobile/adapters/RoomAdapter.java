package com.gotravel.mobile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gotravel.mobile.R;
import com.gotravel.mobile.activities.HotelDetailActivity;
import com.gotravel.mobile.models.Room;

import java.util.List;

/**
 * Created by jhanrojas on 10/10/16.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private final List<Room> roomList;

    public RoomAdapter(List<Room> roomList){
        this.roomList = roomList;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GOTRAVEL","RoomViewHolder onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        Log.d("GOTRAVEL","RoomViewHolder onBindViewHolder");
        holder.room = roomList.get(position);
        holder.roomNameTextView.setText(roomList.get(position).name);
        holder.roomPriceTextView.setText("$ "+roomList.get(position).price.toString());
    }

    @Override
    public int getItemCount() {
        return roomList != null ? roomList.size() : 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView roomNameTextView;
        public final TextView roomPriceTextView;
        public Room room;

        public RoomViewHolder(View view) {
            super(view);
            mView = view;
            roomNameTextView = (TextView) view.findViewById(R.id.roomNameTextView);
            roomPriceTextView = (TextView) view.findViewById(R.id.roomPriceTextView);
        }
    }
}
