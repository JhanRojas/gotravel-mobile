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
import com.gotravel.mobile.fragment.PlaceCatalogFragment;
import com.gotravel.mobile.models.Place;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    Context mContext;
    private final List<Place> places;
    private final PlaceCatalogFragment.OnListFragmentInteractionListener mListener;

    public PlaceAdapter(List<Place> items, PlaceCatalogFragment.OnListFragmentInteractionListener listener, Context context) {
        Log.d("GOTRAVEL", "PlaceAdapter");
        places = items;
        mListener = listener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("GOTRAVEL", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("GOTRAVEL", "onBindViewHolder");
        //holder.hlace = places.get(position);
        Log.d("Gotravel", "PlaceAdapter onBindViewHolder places.id="+holder.place.id);
        holder.placeNameTextView.setText(places.get(position).name);
        Log.d("Gotravel", "PlaceAdapter onBindViewHolder places.id="+holder.place.name);





        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Log.d("Gotravel", "PlaceAdapter onBindViewHolder setOnClickListener places.id="+holder.place.id);
                    mListener.onListFragmentInteraction(holder.place, holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return places != null ? places.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView placeNameTextView;
        public final ImageView placePictureImageView;
        public final LinearLayout placeNameHolder;
        public Place place;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            placeNameHolder = (LinearLayout) view.findViewById(R.id.placeNameHolder);
            placeNameTextView = (TextView) view.findViewById(R.id.placeNameTextView);
            placePictureImageView = (ImageView) view.findViewById(R.id.placePictureImageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + placeNameTextView.getText() + "'";
        }
    }
}
