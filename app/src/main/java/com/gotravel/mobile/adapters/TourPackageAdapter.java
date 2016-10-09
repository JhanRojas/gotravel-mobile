package com.gotravel.mobile.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gotravel.mobile.models.TourPackage;

import java.util.ArrayList;

import com.gotravel.mobile.R;

/**
 * Created by jhanrojas on 8/10/16.
 */

public class TourPackageAdapter extends RecyclerView.Adapter<TourPackageAdapter.ViewHolder> {
    private ArrayList<TourPackage> tourPackages;

    public TourPackageAdapter(ArrayList<TourPackage> tourPackages) {
        this.tourPackages = tourPackages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tourPackageTextView;
        ImageView tourPackageImageView;
        CardView tourPackageCard;

        public ViewHolder(View itemView) {
            super(itemView);
            tourPackageCard = (CardView) itemView.findViewById(R.id.tourPackageCard);
            tourPackageTextView = (TextView) itemView.findViewById(R.id.tourPackageTitleTextView);
            tourPackageImageView = (ImageView) itemView.findViewById(R.id.tourPackageImageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_package_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tourPackageTextView.setText(tourPackages.get(position).title);
        holder.tourPackageImageView.setImageResource(
                Integer.parseInt(tourPackages.get(position).pictureUrl));
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.printf("Selected position: %d%n", position);
                Intent itemIntent = new Intent(view.getContext(), ItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("firstName", tourPackages.get(position).firstName);
                bundle.putString("pictureUrl", tourPackages.get(position).pictureUrl);
                itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return tourPackages.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
