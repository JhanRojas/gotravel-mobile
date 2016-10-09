package com.gotravel.mobile.models;

import android.content.Context;

/**
 * Created by jhanrojas on 8/10/16.
 */

public class TourPackage {

    public String id;
    public String title;
    public String pictureUrl;
    public boolean isFav;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.pictureUrl, "drawable", context.getPackageName());
    }
}
