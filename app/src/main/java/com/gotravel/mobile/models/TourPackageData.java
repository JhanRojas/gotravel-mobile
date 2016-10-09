package com.gotravel.mobile.models;

import java.util.ArrayList;

/**
 * Created by jhanrojas on 9/10/16.
 */

import com.gotravel.mobile.R;

public class TourPackageData {

    public static String[] tourPackageTitleArray = {"Bora Bora", "Canada", "Dubai", "Hong Kong", "Iceland", "India", "Kenya", "London", "Switzerland", "Sydney"};

    public static ArrayList<TourPackage> tourPackageList() {
        ArrayList<TourPackage> list = new ArrayList<>();
        for (int i = 0; i < tourPackageTitleArray.length; i++) {
            TourPackage place = new TourPackage();
            place.title = tourPackageTitleArray[i];
            place.pictureUrl = tourPackageTitleArray[i].replaceAll("\\s+", "").toLowerCase();
            if (i == 2 || i == 5) {
                place.isFav = true;
            }
            list.add(place);
        }
        return (list);
    }

    public static TourPackage getItem(String _id) {
        for (TourPackage tourPackage : tourPackageList()) {
            if (tourPackage.id.equals(_id)) {
                return tourPackage;
            }
        }
        return null;
    }
}
