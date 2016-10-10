package com.gotravel.mobile.fragment.dummy;

import android.content.Intent;

import com.gotravel.mobile.models.Hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class HotelContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Hotel> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Hotel> ITEM_MAP = new HashMap<Integer, Hotel>();

    private static final int COUNT = 25;

    static {
        addItem(new Hotel(1,"Sheraton","5 Entrellas","http://vao.pe/wp-content/uploads/2013/03/sheraton-hoetl.jpg"));
    }

    private static void addItem(Hotel item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
