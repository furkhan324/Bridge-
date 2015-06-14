package com.example.mohammed.bridge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by mohammed on 5/17/15.
 */
public class Login extends Activity{
    private ProgressBar progressBar;
    private int progressStatus = 0;

    private Handler handler = new Handler();


    private TextView logo;
    private TextView sublogo;
    private TextView email;
    private TextView password;
    private TextView join;
    private EditText email2;
    private EditText password2;
    private TextView login;
    private ImageView im;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        logo =(TextView) findViewById(R.id.logo);
        sublogo =(TextView) findViewById(R.id.sublogo);
        email =(TextView) findViewById(R.id.email);
        password =(TextView) findViewById(R.id.password);
        im=(ImageView) findViewById(R.id.imageView4);
        email2 =(EditText) findViewById(R.id.email2);
        password2 =(EditText) findViewById(R.id.password2);
        join =(TextView) findViewById(R.id.join);
        login=(TextView) findViewById(R.id.login);
        Typeface hmBold=Typeface.createFromAsset(getAssets(),"fonts/hmBold.otf");
        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");
        logo.setTypeface(hmBold);
        join.setTypeface(bariol);
        login.setTypeface(bariol);
        sublogo.setTypeface(bariol);
        email.setTypeface(bariol);
        password.setTypeface(bariol);
        email2.setTypeface(bariol);
        password2.setTypeface(bariol);

        ////////////////////////////////////////////////////////////////////////////////////////

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username;String password;
                username = email2.getText().toString();
                password = password2.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            Intent i = new Intent("MAIN");
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),
                                    "Logged in Succesfully."
                                    , Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error logging in.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username; String password;
                username = email2.getText().toString();
                password = password2.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            //start sinch service
                            //start next activity

                            Intent i= new Intent("LOCATION");
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),
                                    "Logged in Succesfully."
                                    , Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error signing up."
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i= new Intent("MAIN");
            startActivity(i);

        }
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