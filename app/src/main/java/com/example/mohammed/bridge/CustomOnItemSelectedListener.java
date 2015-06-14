package com.example.mohammed.bridge;
//Contact()
//Camera
//Location
//Website
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {
public String s;
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        s=parent.getItemAtPosition(pos).toString();
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }
    public String returnS(){
        return s;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}