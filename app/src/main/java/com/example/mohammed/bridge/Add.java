package com.example.mohammed.bridge;
//class that adds events to bridge
//takes user input added modal support as of new 
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.List;

/**
 * Created by mohammed on 6/13/15.
 */
public class Add extends FragmentActivity implements View.OnClickListener, View.OnFocusChangeListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
 Geocoder gc;
    EditText nameedit;
    EditText descedit;
    EditText dateedit;
    EditText timeedit;
    EditText locationedit;

    ImageView enterlocation;
    TextView enterevent;

    Spinner spinner;

    ImageView home;
    ImageView next;


    int mYear;
    int mMonth;
    int mDay;

    Address address;
    int mMinute;
    int mHour;
    LatLng latLng;
    CustomOnItemSelectedListener obj;
    public void addListenerOnSpinnerItemSelection() {
        obj = new CustomOnItemSelectedListener();
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(obj);

    }

    public void onClick(View v){
  if(v==enterlocation){
      String addy = locationedit.getText().toString();
      if(gc.isPresent()){
          try {
              List<Address> list = gc.getFromLocationName(addy, 1);
              //addy to be inserted int the parameter above
              address = list.get(0);
                locationedit.setText(address.toString());
              double lat = address.getLatitude();
              double lng = address.getLongitude();


              latLng = new LatLng(lat, lng);
              MarkerOptions options = new MarkerOptions()
                      .position(latLng)
                      .title("Event Name");
              CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14.0f).build();
              CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
              mMap.addMarker(options);
              mMap.moveCamera(cameraUpdate);

          }
          catch(Throwable throwable){

          }



      }
        }else if(v==enterevent){

        }else if(v==home){
      new AlertDialog.Builder(this)
              .setIcon(android.R.drawable.ic_dialog_alert)
              .setTitle("Exiting Post")
              .setMessage("Are you sure you want to close this activity? You have not finished this post!")
              .setPositiveButton("Yes", new DialogInterface.OnClickListener()
              {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      finish();
                  }

              })
              .setNegativeButton("No", null)
              .show();

        }else if(v==next){

      Toast.makeText(this,
              "Hi this is running",
              Toast.LENGTH_LONG).show();
      ParseObject entry = new ParseObject("Post");
      entry.put("state",address.getAdminArea().toString());
      entry.put("city",address.getLocality().toString());
      entry.put("picture",ParseUser.getCurrentUser().get("profile").toString());
      entry.put("poster",ParseUser.getCurrentUser().getUsername().toString());
      entry.put("currentuserid",ParseUser.getCurrentUser().getObjectId());
        entry.put("name",nameedit.getText().toString());
      entry.put("desc",descedit.getText().toString());
      entry.put("date",dateedit.getText().toString());
      entry.put("time",timeedit.getText().toString());
      entry.put("address",locationedit.getText().toString());
      entry.put("lat",latLng.latitude);
      entry.put("lng",latLng.longitude);
      //ParseGeoPoint point = new ParseGeoPoint(latLng.latitude, latLng.longitude);
      //entry.put("location",point);
      //entry
      entry.put("type","post");

      entry.put("category",obj.returnS());
      try {

          entry.save();
          Log.e("TAG2",entry.getObjectId());

          Toast.makeText(this,
                  entry.toString(),
                  Toast.LENGTH_LONG).show();
         // Log.e("TAG2",entry.getObjectId());
          finish();
      }
     catch(Exception e){
         Toast.makeText(this,
                 "We got an error nigga",
                 Toast.LENGTH_LONG).show();
         Log.e("TAG", Log.getStackTraceString(e));
     }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);


        setUpMapIfNeeded();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        gc = new Geocoder(this);

       home= (ImageView) findViewById(R.id.home);
        next=(ImageView) findViewById(R.id.next);
home.setOnClickListener(this);
        next.setOnClickListener(this);
        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");
        Typeface hm=Typeface.createFromAsset(getAssets(),"fonts/exo.otf");
        Typeface hm2=Typeface.createFromAsset(getAssets(),"fonts/exoi.otf");
        spinner=(Spinner) findViewById(R.id.spinner);
        addListenerOnSpinnerItemSelection();

        TextView title = (TextView) findViewById(R.id.post);

        title.setTypeface(hm);

        TextView poster = (TextView) findViewById(R.id.poster);

        poster.setTypeface(hm);






        enterlocation = (ImageView) findViewById(R.id.enterlocation);
        enterevent = (TextView) findViewById(R.id.enterevent);
enterevent.setOnClickListener(this);
        enterlocation.setOnClickListener(this);
        nameedit = (EditText) findViewById(R.id.title);
        descedit = (EditText) findViewById(R.id.desc);
        dateedit = (EditText) findViewById(R.id.date);




        timeedit = (EditText) findViewById(R.id.time);
        locationedit = (EditText) findViewById(R.id.location);


        nameedit.setTypeface(bariol);

        descedit.setTypeface(bariol);

        dateedit.setTypeface(bariol);
        timeedit.setOnFocusChangeListener(this);

        timeedit.setTypeface(bariol);
        dateedit.setOnFocusChangeListener(this);

        locationedit.setTypeface(bariol);

//set up maps
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        if(v==timeedit){
        if (hasFocus == true)
        {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            timeedit.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
    }
        else if(v==dateedit){
            if (hasFocus == true)
            {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                dateedit.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
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
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

}
