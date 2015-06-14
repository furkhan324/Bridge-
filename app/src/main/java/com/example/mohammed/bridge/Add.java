package com.example.mohammed.bridge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import com.parse.ParseException;

/**
 * Created by mohammed on 6/13/15.
 */
public class Add extends Activity {
TextView number;
    TextView title;
    TextView subtitle;
    TextView subtitle2;
    EditText name;
    EditText desc;
    TextView subtitle3;
    Spinner spinner1;
    String id;
    private ParseObject object;
    CustomOnItemSelectedListener obj = new CustomOnItemSelectedListener();
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner);

        spinner1.setOnItemSelectedListener(obj);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        object= new ParseObject("Bridge");
        //objects being posted need to have the following info:
        // name of poster, catorgory, date and time, location, contact, info
        object.put("posterId", ParseUser.getCurrentUser().getUsername());//check 1


        Typeface bariol=Typeface.createFromAsset(getAssets(),"fonts/bariol.otf");
number=(TextView) findViewById(R.id.number);
        subtitle=(TextView) findViewById(R.id.subtitle);
        title =(TextView) findViewById(R.id.title);
        subtitle2=(TextView) findViewById(R.id.subtitle2);
        name=(EditText) findViewById(R.id.name);
        desc=(EditText) findViewById(R.id.desc);
        subtitle3=(TextView) findViewById(R.id.subtitle3);
        //subtitle2=(TextView) findViewById(R.id.subtitle2);
        number.setTypeface(bariol);
        subtitle.setTypeface(bariol);
        name.setTypeface(bariol);
        desc.setTypeface(bariol);
        subtitle3.setTypeface(bariol);
        title.setTypeface(bariol);
        addListenerOnSpinnerItemSelection();
        subtitle2.setTypeface(bariol);
        ImageView next =(ImageView) findViewById(R.id.next);
        ImageView home =(ImageView) findViewById(R.id.home);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               object.put("category", obj.returnS());
                object.put("desc", desc.getText().toString());
                try{
                object.save();}
                catch(Exception e){
                    e.printStackTrace();
                }
                Intent i = new Intent("ADD2");

                i.putExtra("objectId", object.getObjectId());
                Log.e("id", object.getObjectId());

                startActivity(i);
            }
        });


    }
}
