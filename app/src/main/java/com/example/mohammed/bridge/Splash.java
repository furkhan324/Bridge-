package com.example.mohammed.bridge;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by mohammed on 5/17/15.
 */
public class Splash extends Activity{
    private ProgressBar progressBar;
    private int progressStatus = 0;

    private Handler handler = new Handler();


    private TextView logo;
    private TextView sublogo;
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash5);



        //TextView tv=(TextView) findViewById(R.id.textView);
        //TextView logo=(TextView) findViewById(R.id.logo1);
        //Typeface generica2=Typeface.createFromAsset(getAssets(),"fonts/GenericaBold.otf");
        //Typeface generica=Typeface.createFromAsset(getAssets(),"fonts/Generica.otf");
        //logo.setTypeface(generica2);
        //tv.setTypeface(generica);
        //  progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Start long running operation in a background thread
        //tv.setTypeface(generica);
        ImageView splashImage = (ImageView) Splash.this
                .findViewById(R.id.imageView20);

// make the splash image invisible

//splashImage.setVisibility(View.GONE);
        AnimationSet animation2 = new AnimationSet(true);
        animation2.addAnimation(new AlphaAnimation(1.0F, 0.0F));
        animation2.addAnimation(new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f)); // Change args as desired
        animation2.setDuration(1000);
        splashImage.startAnimation(animation2);
        new Thread(new Runnable() {
            public void run() {



                try {
                    // Sleep for 200 milliseconds.
                    //Just to display the progress slowly
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isOnline()) {
                    Intent i = new Intent("START");

                    startActivity(i);
                }else{

                }

        }}).start();
        if(!isOnline()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry, Bridge needs an internet connection to continue!" +
                            ""
                    , Toast.LENGTH_LONG).show();
        }

}

    protected void onPause(){

       super.onPause();
    finish();
    }



}