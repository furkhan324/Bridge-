package com.example.mohammed.bridge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;

/**
 * Created by mohammed on 6/13/15.
 */
public class Add3 extends Activity {
    TextView number;
    TextView title;
    TextView subtitle;
    TextView subtitle2;
    EditText name;
    String id;
    ImageView next;
    TimePicker tp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);
        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");
        number=(TextView) findViewById(R.id.number);
        tp =(TimePicker) findViewById(R.id.tp);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("objectId");
        }
        title =(TextView) findViewById(R.id.title);
        next=(ImageView) findViewById(R.id.next);
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
                            String selectedDate = tp.getCurrentHour() + " "+ tp.getCurrentMinute();
                            object.put("time", selectedDate);
                            object.saveInBackground();
                            Intent i = new Intent("ADDLOCATION");
                            i.putExtra("objectId", object.getObjectId());
                            startActivity(i);                        } else {

                        }
                    }
                });

            }
        });

    }
}
