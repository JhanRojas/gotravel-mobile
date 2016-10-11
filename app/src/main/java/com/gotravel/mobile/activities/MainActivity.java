package com.gotravel.mobile.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gotravel.mobile.R;
import com.gotravel.mobile.adapters.HotelAdapter;
import com.gotravel.mobile.adapters.TourPackageAdapter;
import com.gotravel.mobile.fragment.HotelCatalogFragment;
import com.gotravel.mobile.fragment.PlaceCatalogFragment;
import com.gotravel.mobile.models.Hotel;
import com.gotravel.mobile.models.TourPackage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HotelCatalogFragment.OnListFragmentInteractionListener {

    private Toolbar toolbar;
    private ArrayList<TourPackage> tourPackages;
    private RecyclerView tourPackageRecyclerView;
    private RecyclerView hotelCatalogRecyclerView;
    //private RecyclerView.Adapter tourPackageAdapter;
    //private RecyclerView.LayoutManager tourPackageLayoutManager;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TourPackageAdapter tourPackageAdapter;
    private Menu menu;
    private boolean isListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setUpActionBar();

        /*Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = HotelCatalogFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();*/


/*
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        tourPackageRecyclerView = (RecyclerView) findViewById(R.id.tourPackageRecyclerView);
        tourPackageRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        tourPackageRecyclerView.setHasFixedSize(true); //Data size is fixed - improves performance
        tourPackageAdapter = new TourPackageAdapter(this);
        tourPackageRecyclerView.setAdapter(tourPackageAdapter);

        tourPackageAdapter.setOnItemClickListener(onItemClickListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        isListView = true;
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setUpActionBar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tourPackages = new ArrayList<>();
        initializeData();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        tourPackageRecyclerView = (RecyclerView) findViewById(R.id.tourPackageRecyclerView);
        tourPackageRecyclerView.setHasFixedSize(true);
        tourPackageLayoutManager = new LinearLayoutManager(this);
        tourPackageRecyclerView.setLayoutManager(tourPackageLayoutManager);
        tourPackageAdapter = new TourPackageAdapter(tourPackages);
        tourPackageRecyclerView.setAdapter(tourPackageAdapter);

        isListView = true;*/

    }

    TourPackageAdapter.OnItemClickListener onItemClickListener = new TourPackageAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent transitionIntent = new Intent(MainActivity.this, TourPackageDetailActivity.class);
            transitionIntent.putExtra(TourPackageDetailActivity.EXTRA_PARAM_ID, position);
            ImageView placeImage = (ImageView) v.findViewById(R.id.placeImage);
            LinearLayout placeNameHolder = (LinearLayout) v.findViewById(R.id.placeNameHolder);

            View navigationBar = findViewById(android.R.id.navigationBarBackground);
            View statusBar = findViewById(android.R.id.statusBarBackground);

            Pair<View, String> imagePair = Pair.create((View) placeImage, "tImage");
            Pair<View, String> holderPair = Pair.create((View) placeNameHolder, "tNameHolder");
            Pair<View, String> navPair = Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
            Pair<View, String> statusPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
            Pair<View, String> toolbarPair = Pair.create((View)toolbar, "tActionBar");

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, imagePair, holderPair, navPair, statusPair, toolbarPair);
            ActivityCompat.startActivity(MainActivity.this, transitionIntent, options.toBundle());
        }
    };



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d("Gotravel", "onNavigationItemSelected");
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_tour_package) {
            // Handle the camera action
        } else if (id == R.id.nav_flight) {

        } else if (id == R.id.nav_hotel) {
            Log.d("Gotravel", "onNavigationItemSelected - nav_hotel");
            fragmentClass = HotelCatalogFragment.class;
        } else if (id == R.id.nav_restaurant) {

        } else if (id == R.id.nav_place) {
            Log.d("Gotravel", "onNavigationItemSelected - nav_place");
            fragmentClass = PlaceCatalogFragment.class;

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setElevation(7);
        }
    }

    private void toggle() {
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (isListView) {
            mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Show as list");
            isListView = false;
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Show as grid");
            isListView = true;
        }
    }


    @Override
    public void onListFragmentInteraction(Hotel item, HotelAdapter.ViewHolder v) {

        Intent transitionIntent = new Intent(MainActivity.this, HotelDetailActivity.class);
        transitionIntent.putExtra(HotelDetailActivity.EXTRA_PARAM_ID, item.id);
        transitionIntent.putExtra(HotelDetailActivity.EXTRA_PARAM_NAME, item.name);
        ImageView placeImage = (ImageView) v.hotelPictureImageView;
        LinearLayout placeNameHolder = (LinearLayout) v.hotelNameHolder;

        View navigationBar = findViewById(android.R.id.navigationBarBackground);
        View statusBar = findViewById(android.R.id.statusBarBackground);

        Pair<View, String> imagePair = Pair.create((View) placeImage, "tImage");
        Pair<View, String> holderPair = Pair.create((View) placeNameHolder, "tNameHolder");
        Pair<View, String> navPair = Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
        Pair<View, String> statusPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
        Pair<View, String> toolbarPair = Pair.create((View)toolbar, "tActionBar");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, imagePair, holderPair, navPair, statusPair, toolbarPair);

        ActivityCompat.startActivity(MainActivity.this, transitionIntent, options.toBundle());

    }
}
