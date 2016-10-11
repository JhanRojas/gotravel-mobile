package com.gotravel.mobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gotravel.mobile.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.gotravel.mobile.fragment.FlightCatalogFragment;
import com.gotravel.mobile.models.Flight;
import java.util.List;
/**
 * Created by RGodoy on 11/10/2016.
 */

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {
    Context mContext;
    private final List<Flight> flights;
    private final FlightCatalogFragment.OnListFlightFragmentInteractionListener mListener;

    public FlightAdapter(List<Flight> items, FlightCatalogFragment.OnListFlightFragmentInteractionListener listener, Context context) {
        Log.d("GOTRAVEL", "FlightAdapter");
        flights = items;
        mListener = listener;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GOTRAVEL", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_flight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("GOTRAVEL", "onBindViewHolder");
        holder.flight = flights.get(position);
        Log.d("Gotravel", "FlightAdapter onBindViewHolder flights.id="+holder.flight.id);
        holder.flightNameTextView.setText(flights.get(position).originCity+" "+flights.get(position).destinationCity);
        Log.d("Gotravel", "FlightAdapter onBindViewHolder flights.id="+holder.flight.airline_id);
        Log.d("Gotravel", "FlightAdapter onBindViewHolder flights.id="+holder.flight.pictureUrl);


        Picasso.with(mContext).load(flights.get(position).pictureUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.flightPictureImageView.setImageBitmap(bitmap);

                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                        holder.flightNameHolder.setBackgroundColor(mutedLight);
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
                    Log.d("Gotravel", "FlightAdapter onBindViewHolder setOnClickListener flights.id="+holder.flight.id);
                    mListener.onListFragmentInteraction(holder.flight, holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return flights != null ? flights.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView flightNameTextView;
        public final ImageView flightPictureImageView;
        public final LinearLayout flightNameHolder;
        public Flight flight;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            flightNameHolder = (LinearLayout) view.findViewById(R.id.flightNameHolder);
            flightNameTextView = (TextView) view.findViewById(R.id.flightNameTextView);
            flightPictureImageView = (ImageView) view.findViewById(R.id.flightPictureImageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + flightNameTextView.getText() + "'";
        }
    }

}
