package com.example.mohammed.bridge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;

/**
 * Created by mohammed on 6/13/15.
 */
public class Add2 extends Activity {
    TextView number;
    TextView title;
    TextView subtitle;
    TextView subtitle2;
    EditText name;
    String id;
ImageView next;
    DatePicker dp;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add2);
        dp =(DatePicker) findViewById(R.id.dp);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        id = extras.getString("objectId");
            Log.e("id2", id);
        }
        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");
        number=(TextView) findViewById(R.id.number);
        next=(ImageView) findViewById(R.id.next);
        title =(TextView) findViewById(R.id.title);

        //subtitle2=(TextView) findViewById(R.id.subtitle2);
        number.setTypeface(bariol);

        title.setTypeface(bariol);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Bridge");
                query.getInBackground(id, new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            String selectedDate = String.valueOf(dp.getDayOfMonth()) + "/" + String.valueOf(dp.getMonth()) + "/" + String.valueOf(dp.getYear());
                            object.put("date", selectedDate);
                            object.saveInBackground();
                            Intent i = new Intent("ADD3");
                            i.putExtra("objectId", object.getObjectId());
                            startActivity(i);
                        } else {
e.printStackTrace();
                        }
                    }
                });

            }
        });


    }
}