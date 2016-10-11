package com.gotravel.mobile.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gotravel.mobile.R;
import com.gotravel.mobile.adapters.HotelAdapter;
import com.gotravel.mobile.models.Hotel;
import com.gotravel.mobile.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HotelCatalogFragment extends Fragment {

    ArrayList<Hotel> hotels = new ArrayList<>();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HotelCatalogFragment() {
        Log.d("GOTRAVEL", "HotelCatalogFragment");
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HotelCatalogFragment newInstance(int columnCount) {
        Log.d("GOTRAVEL", "HotelCatalogFragment newInstance");
        HotelCatalogFragment fragment = new HotelCatalogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("GOTRAVEL", "HotelCatalogFragment onCreate");
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            getHotels();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("GOTRAVEL", "HotelCatalogFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_hotel_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            /*(if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                Log.d("GOTRAVEL","LinearLayoutManager");
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                Log.d("GOTRAVEL","GridLayoutManager");
            }*/
            //Load JSON
            getHotels();

            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView = (RecyclerView) view.findViewById(R.id.hotelCatalogRecyclerView);
            recyclerView.setLayoutManager(mStaggeredLayoutManager);
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(new HotelAdapter(hotels, mListener, this.getContext()));
        }
        return view;
    }

    public void getHotels(){
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, Constants.RESTFUL_MAIN_URL+Constants.RESTFUL_HOTELS_LIST_PATH, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!

                hotels.clear();
                try {

                    for(int i=0;i<response.length();i++){

                        JSONObject hotelJson = response.getJSONObject(i);
                        Hotel hotelTemp = new Hotel(hotelJson.getInt("id"),hotelJson.getString("name"),hotelJson.getString("description"), hotelJson.getString("pictureUrl"));
                        Log.d("Gotravel","hotel.name"+hotelTemp.name);
                        hotels.add(hotelTemp);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Allow the adapter refresh their content
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this.getContext()).add(jsonRequest);

    }

    @Override
    public void onAttach(Context context) {
        Log.d("GOTRAVEL", "HotelCatalogFragment onAttach");
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Hotel item, HotelAdapter.ViewHolder viewHolder);
    }


}
