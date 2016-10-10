package com.gotravel.mobile.fragment.dummy;

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
    public static final Map<String, Hotel> ITEM_MAP = new HashMap<String, Hotel>();

    private static final int COUNT = 25;

    static {
        addItem(new Hotel(String.valueOf(String.valueOf(1)),"Sheraton","5 Entrellas","http://vao.pe/wp-content/uploads/2013/03/sheraton-hoetl.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(2)),"Westin","5 Entrellas","http://www.gym.com.pe/images/galeria/edificaciones/westin/westin_lima_big.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(3)),"Marriott","5 Entrellas","http://techdrive.co/wp-content/uploads/2014/10/http-cache.marriott.com-propertyimages-m-miaap-miaap_main01.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(4)),"Sumaq","5 Entrellas","http://www.luxurylatinamerica.com/peru/photos2/sumaq-exterior.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(5)),"Marina","5 Entrellas","https://media-cdn.tripadvisor.com/media/photo-s/01/c7/f5/57/mancora-marina-hotel.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(6)),"Marriott","5 Entrellas","http://techdrive.co/wp-content/uploads/2014/10/http-cache.marriott.com-propertyimages-m-miaap-miaap_main01.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(7)),"Sumaq","5 Entrellas","http://www.luxurylatinamerica.com/peru/photos2/sumaq-exterior.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(8)),"Marina","5 Entrellas","https://media-cdn.tripadvisor.com/media/photo-s/01/c7/f5/57/mancora-marina-hotel.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(9)),"Sheraton","5 Entrellas","http://vao.pe/wp-content/uploads/2013/03/sheraton-hoetl.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(10)),"Westin","5 Entrellas","http://www.gym.com.pe/images/galeria/edificaciones/westin/westin_lima_big.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(11)),"Marriott","5 Entrellas","http://techdrive.co/wp-content/uploads/2014/10/http-cache.marriott.com-propertyimages-m-miaap-miaap_main01.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(12)),"Sumaq","5 Entrellas","http://www.luxurylatinamerica.com/peru/photos2/sumaq-exterior.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(13)),"Marina","5 Entrellas","https://media-cdn.tripadvisor.com/media/photo-s/01/c7/f5/57/mancora-marina-hotel.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(14)),"Marriott","5 Entrellas","http://techdrive.co/wp-content/uploads/2014/10/http-cache.marriott.com-propertyimages-m-miaap-miaap_main01.jpg"));
        addItem(new Hotel(String.valueOf(String.valueOf(15)),"Sumaq","5 Entrellas","http://www.luxurylatinamerica.com/peru/photos2/sumaq-exterior.jpg"));
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
