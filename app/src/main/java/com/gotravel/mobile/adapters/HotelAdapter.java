package com.gotravel.mobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gotravel.mobile.R;
import com.gotravel.mobile.fragment.HotelCatalogFragment;
import com.gotravel.mobile.models.Hotel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by jhanrojas on 8/10/16.
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    Context mContext;
    private final List<Hotel> hotels;
    private final HotelCatalogFragment.OnListFragmentInteractionListener mListener;

    public HotelAdapter(List<Hotel> items, HotelCatalogFragment.OnListFragmentInteractionListener listener, Context context) {
        hotels = items;
        mListener = listener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.hotel = hotels.get(position);
        Log.d("Gotravel", "HotelAdapter onBindViewHolder hotels.id="+holder.hotel.id);
        holder.hotelNameTextView.setText(hotels.get(position).name);

        Picasso.with(mContext).load(hotels.get(position).pictureUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.hotelPictureImageView.setImageBitmap(bitmap);

                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                        holder.hotelNameHolder.setBackgroundColor(mutedLight);
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Log.d("Gotravel", "HotelAdapter onBindViewHolder setOnClickListener hotels.id="+holder.hotel.id);
                    mListener.onListFragmentInteraction(holder.hotel, holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView hotelNameTextView;
        public final ImageView hotelPictureImageView;
        public final LinearLayout hotelNameHolder;
        public Hotel hotel;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            hotelNameHolder = (LinearLayout) view.findViewById(R.id.hotelNameHolder);
            hotelNameTextView = (TextView) view.findViewById(R.id.hotelNameTextView);
            hotelPictureImageView = (ImageView) view.findViewById(R.id.hotelPictureImageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + hotelNameTextView.getText() + "'";
        }
    }
}
