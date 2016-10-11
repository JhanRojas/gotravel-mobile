package com.gotravel.mobile.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.gotravel.mobile.adapters.FlightAdapter;
import com.gotravel.mobile.models.Flight;
import com.gotravel.mobile.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.gotravel.mobile.R;
import java.util.ArrayList;
/**
 * Created by RGodoy on 11/10/2016.
 */

public class FlightCatalogFragment extends Fragment {
    ArrayList<Flight> flights = new ArrayList<>();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFlightFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    public FlightCatalogFragment() {
        Log.d("GOTRAVEL", "FlightCatalogFragment");
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FlightCatalogFragment newInstance(int columnCount) {
        Log.d("GOTRAVEL", "FlightCatalogFragment newInstance");
        FlightCatalogFragment fragment = new FlightCatalogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("GOTRAVEL", "FlightCatalogFragment onCreate");
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            getFlights();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("GOTRAVEL", "FlightCatalogFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //Load JSON
            getFlights();

            recyclerView.setAdapter(new FlightAdapter(flights, mListener, this.getContext()));
        }
        return view;
    }


    public void getFlights(){
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, Constants.RESTFUL_MAIN_URL+Constants.RESTFUL_FLIGHTS_LIST_PATH, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!

                flights.clear();
                try {

                    for(int i=0;i<response.length();i++){

                        JSONObject flightJson = response.getJSONObject(i);
                        Flight flightTemp = new Flight(flightJson.getInt("id"),flightJson.getString("origin_country"),flightJson.getString("origin_state"),flightJson.getString("origin_city"),flightJson.getString("destination_country"),flightJson.getString("destination_state"),flightJson.getString("destination_city"),flightJson.getString("departure"),flightJson.getString("returning"),flightJson.getDouble("adult_price") ,flightJson.getDouble("minor_price"),flightJson.getInt("capacity"),flightJson.getInt("airline_id"),flightJson.getString("pictureUrl"));
                        Log.d("Gotravel","flight.airline_id"+flightTemp.airline_id);
                        flights.add(flightTemp);
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
        Log.d("GOTRAVEL", "FlightCatalogFragment onAttach");
        super.onAttach(context);
        if (context instanceof OnListFlightFragmentInteractionListener) {
            mListener = (OnListFlightFragmentInteractionListener) context;
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
    public interface OnListFlightFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Flight item, FlightAdapter.ViewHolder viewHolder);
    }
}
