package com.example.mohammed.bridge;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mock extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
    setContentView(R.layout.activity_event);
        TextView post= (TextView) findViewById(R.id.post);
        TextView title= (TextView) findViewById(R.id.title);
        TextView poster= (TextView) findViewById(R.id.poster);
        TextView datetime= (TextView) findViewById(R.id.datetime);
        TextView desc= (TextView) findViewById(R.id.desc);
        TextView join= (TextView) findViewById(R.id.join);
        TextView joiners= (TextView) findViewById(R.id.joiners);

        Typeface lb= Typeface.createFromAsset(getAssets(), "fonts/lb.ttf");
        Typeface ll= Typeface.createFromAsset(getAssets(), "fonts/ll.ttf");

        post.setTypeface(ll);
        join.setTypeface(ll);

        title.setTypeface(ll);
        poster.setTypeface(ll);
        datetime.setTypeface(ll);
        desc.setTypeface(ll);
        joiners.setTypeface(ll);


        setUpMapIfNeeded(); */
/*
        setContentView(R.layout.splash2);
        EditText username= (EditText) findViewById(R.id.username);
        EditText password= (EditText) findViewById(R.id.password);
        TextView login= (TextView) findViewById(R.id.login);
        TextView tandc= (TextView) findViewById(R.id.tandc);
     ;

        Typeface lb= Typeface.createFromAsset(getAssets(), "fonts/lb.ttf");
        Typeface ll= Typeface.createFromAsset(getAssets(), "bariol/ll.otf");

        login.setTypeface(ll);
        password.setTypeface(ll);
        username.setTypeface(ll);
        tandc.setTypeface(ll);*/


        setContentView(R.layout.splash3);
        EditText username= (EditText) findViewById(R.id.username);
        EditText password= (EditText) findViewById(R.id.password);
           EditText password2= (EditText) findViewById(R.id.password2);
        TextView signup= (TextView) findViewById(R.id.signup);
       // TextView tandc= (TextView) findViewById(R.id.tandc);
     ;

        Typeface lb= Typeface.createFromAsset(getAssets(), "fonts/lb.ttf");
        Typeface ll= Typeface.createFromAsset(getAssets(), "fonts/bariol.otf");

        signup.setTypeface(ll);
        password2.setTypeface(ll);
        password.setTypeface(ll);
        username.setTypeface(ll);
        //tandc.setTypeface(ll);

/*

        Typeface ll= Typeface.createFromAsset(getAssets(), "fonts/bariol.otf");

        setContentView(R.layout.splash4);
        TextView sublogo= (TextView) findViewById(R.id.sublogo);
        sublogo.setTypeface(ll); */


    }

    @Override
    protected void onResume() {
        super.onResume();
       // setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
