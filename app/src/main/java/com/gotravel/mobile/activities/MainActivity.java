package com.gotravel.mobile.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.gotravel.mobile.R;
import com.gotravel.mobile.adapters.ImageAdapter;
import com.gotravel.mobile.adapters.TourPackageAdapter;
import com.gotravel.mobile.models.TourPackage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<TourPackage> tourPackages;
    private RecyclerView tourPackageRecyclerView;
    private RecyclerView.Adapter tourPackageAdapter;
    private RecyclerView.LayoutManager tourPackageLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        tourPackageRecyclerView = (RecyclerView) findViewById(R.id.tourPackageRecyclerView);
        tourPackageRecyclerView.setHasFixedSize(true);
        tourPackageLayoutManager = new LinearLayoutManager(this);
        tourPackageRecyclerView.setLayoutManager(tourPackageLayoutManager);
        tourPackageAdapter = new TourPackageAdapter(tourPackages);
        tourPackageRecyclerView.setAdapter(tourPackageAdapter);

    }

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tour_package) {
            // Handle the camera action
        } else if (id == R.id.nav_flight) {

        } else if (id == R.id.nav_hotel) {

        } else if (id == R.id.nav_restaurant) {

        } else if (id == R.id.nav_place) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initializeData() {
        tourPackages = new ArrayList<>();
        tourPackages.add(new TourPackage("Cuzco", Integer.toString(R.mipmap.cuzco)));
        tourPackages.add(new TourPackage("Lima", Integer.toString(R.mipmap.cuzco)));
        tourPackages.add(new TourPackage("Piura", Integer.toString(R.mipmap.cuzco)));
        tourPackages.add(new TourPackage("Ica", Integer.toString(R.mipmap.cuzco)));
        tourPackages.add(new TourPackage("Tacna", Integer.toString(R.mipmap.cuzco)));
    }
}
