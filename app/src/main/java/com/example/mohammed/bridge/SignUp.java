package com.example.mohammed.bridge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by mohammed on 5/17/15.
 */
public class SignUp extends Activity{
    private ProgressBar progressBar;
    private int progressStatus = 0;
Activity current;
    private Handler handler = new Handler();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash3);
        final EditText username= (EditText) findViewById(R.id.username);
       final EditText password= (EditText) findViewById(R.id.password);
       final EditText passwordalt=(EditText) findViewById(R.id.password2);
        TextView signup= (TextView) findViewById(R.id.signup);
        TextView sublogo= (TextView) findViewById(R.id.sublogo);
        TextView tandc= (TextView) findViewById(R.id.login);
        ImageView signup2 = (ImageView) findViewById(R.id.signup2);
        current=this;

        Typeface lb= Typeface.createFromAsset(getAssets(), "fonts/lb.ttf");
        Typeface ll= Typeface.createFromAsset(getAssets(), "fonts/bariol.otf");

        signup.setTypeface(ll);
        password.setTypeface(ll);
        username.setTypeface(ll);
        passwordalt.setTypeface(ll);
        tandc.setTypeface(ll);
        sublogo.setTypeface(ll);
        ///////////////////////////////////////////////////////

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("")||password.getText().toString().equals("")||passwordalt.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Fields need to be filled out"
                            , Toast.LENGTH_LONG).show();
                }
                if(!password.getText().toString().equals(passwordalt.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Passwords don't match"
                            , Toast.LENGTH_LONG).show();
                }
                else{   String username2; String password2;
                    username2 = username.getText().toString();
                    password2 = password.getText().toString();
                    ParseUser user = new ParseUser();
                    user.setUsername(username2);
                    user.setPassword(password2);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(com.parse.ParseException e) {
                            if (e == null) {
                                //start sinch service
                                //start next activity

                                Intent i = new Intent("LOCATION");
                                startActivity(i);
                                Toast.makeText(getApplicationContext(),
                                        "Signed up Succesfully."
                                        , Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "There was an error signing up. Try a different username or password!" +
                                                ""
                                        , Toast.LENGTH_LONG).show();
                                Log.e("TAG", Log.getStackTraceString(e));
                            }
                        }
                    });
            }}
        });
        tandc.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent i= new Intent("LOGIN");
                startActivity(i);
            }
        });
/*
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i= new Intent("MAIN");
            startActivity(i);}  */



        ///////////////////////////////////////////////////////////////////////////////////////
        //TextView tv=(TextView) findViewById(R.id.textView);
        //TextView logo=(TextView) findViewById(R.id.logo1);
        //Typeface generica2=Typeface.createFromAsset(getAssets(),"fonts/GenericaBold.otf");
        //Typeface generica=Typeface.createFromAsset(getAssets(),"fonts/Generica.otf");
        //logo.setTypeface(generica2);
        //tv.setTypeface(generica);
        //  progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Start long running operation in a background thread
        /*
        new Thread(new Runnable() {
            public void run() {



                try {
                    // Sleep for 200 milliseconds.
                    //Just to display the progress slowly
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i= new Intent("LOCATION");
                startActivity(i);
            }
        }).start();*/

    }
    protected void onPause(){

        super.onPause();
        finish();
    }


}