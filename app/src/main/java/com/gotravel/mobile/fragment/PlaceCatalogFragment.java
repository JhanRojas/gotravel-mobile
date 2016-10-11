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
import com.gotravel.mobile.adapters.PlaceAdapter;
import com.gotravel.mobile.models.Place;
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
public class PlaceCatalogFragment extends Fragment {

    ArrayList<Place> places = new ArrayList<>();
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
    public PlaceCatalogFragment() {
        Log.d("GOTRAVEL", "PlaceCatalogFragment");
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlaceCatalogFragment newInstance(int columnCount) {
        Log.d("GOTRAVEL", "PlaceCatalogFragment newInstance");
        PlaceCatalogFragment fragment = new PlaceCatalogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("GOTRAVEL", "PlaceCatalogFragment onCreate");
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            getPlaces();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("GOTRAVEL", "PlaceCatalogFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);

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
            getPlaces();

            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView = (RecyclerView) view.findViewById(R.id.placeCatalogRecyclerView);
            recyclerView.setLayoutManager(mStaggeredLayoutManager);
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(new PlaceAdapter(places, mListener, this.getContext()));
        }
        return view;
    }

    public void getPlaces(){
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, Constants.RESTFUL_MAIN_URL+Constants.RESTFUL_PLACES_LIST_PATH, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!

                places.clear();
                try {

                    for(int i=0;i<response.length();i++){

                        JSONObject placeJson = response.getJSONObject(i);
                        Place placeTemp = new Place(placeJson.getInt("id"),placeJson.getString("name"),placeJson.getString("description"),placeJson.getString("activity"),placeJson.getDouble("price"));
                        Log.d("Gotravel","place.name"+placeTemp.name);
                        places.add(placeTemp);
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
        Log.d("GOTRAVEL", "PlaceCatalogFragment onAttach");
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
        void onListFragmentInteraction(Place item, PlaceAdapter.ViewHolder viewHolder);
    }


}
