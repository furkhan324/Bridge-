package com.example.mohammed.bridge;



import android.app.Application;


import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by mohammed on 5/14/15.
 */
public class BridgeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       // ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, "9HZ7YpqboqbEiBlJwDYVNbS7gi84tYM220BIQy7n", "QeT02o6A7zIOTVYKpBtl0AedjXKJh3IfedXihHTM");
    }
}