package com.example.mohammed.bridge;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mohammed on 5/17/15.
 */
public class Home3 extends Fragment {



    private String currentUserId;

    private List<ParseObject> entries;
    private userListAdapter listAdapter;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_home,container,false);
        /////////////////done setting interface///////////////////////


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        try {
            Log.e("TAG", "THis is the count "+query.count());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        query.setLimit(10);


        try{
            entries=query.find();}
        catch(Exception e){}
        listAdapter =new userListAdapter(entries);
//        Log.e("TAG", entries.toString());

        ListView list = (ListView) v.findViewById(R.id.listView2);

  //      Log.e("TAG", entries.toString());
        if(entries==null){
Log.e("TAG", "ENTRIES IS STILL NULL");
        }
        else{
        list.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
        /////////////////////////onClickoftheuserlist//////////////////////
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
            Intent i = new Intent("EVENT");
            startActivity(i);
                

            }
        });}
        return v;
    }

////////////////////setting interface///////////////////////////



    ///////////////////////////////////////////////////////////////////




        /*
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < userList.size(); i++) {
                        names.add(userList.get(i).getUsername().toString());
                        users.add(userList.get(i));
                    }
                    usersListView = (ListView) findViewById(R.id.listView2);
                    namesArrayAdapter =
                            new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.list_item, names);
                    usersListView.setAdapter(namesArrayAdapter);
                    usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                            //   openConversation(names, i);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error loading user list",
                            Toast.LENGTH_LONG).show();
                }
            }
        });*/






    public class userListAdapter extends BaseAdapter{
        List<ParseObject> entries2;

        public userListAdapter(List<ParseObject> users){

            entries2=users;

        }
        public int getCount() {
            // TODO Auto-generated method stub
            return entries2.size();
        }

        @Override
        public ParseObject getItem(int arg0) {
            // TODO Auto-generated method stub
            return entries2.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }
/*
        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            if(arg1==null)
            {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.list_item, arg2,false);
            }

            Typeface bariol=Typeface.createFromAsset(getActivity().getAssets(), "fonts/bariol.otf");

            TextView icon=(TextView) arg1.findViewById(R.id.textView7);
            TextView chapterName = (TextView)arg1.findViewById(R.id.textView1);
            TextView chapterDesc = (TextView)arg1.findViewById(R.id.textView2);

            ImageView iconImage=(ImageView) arg1.findViewById(R.id.imageView1);




            chapterName.setTypeface(bariol);
            chapterDesc.setTypeface(bariol);
            //icon.setTypeface(bariol);
            ParseUser chapter = users2.get(arg0);


            //icon.setText(chapter.getUsername().toString().substring(0, 1).toUpperCase());
            chapterName.setText("Test Volunteering Opp");
            chapterDesc.setText("Testing beta succesfull");
            int x=arg0;







            return arg1;
        }
*/
        @Override
        public View getView(final int position, View convertView, ViewGroup parent){

            RelativeLayout layout = null;

            if (convertView == null) {
                // inflating the row
                LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = (RelativeLayout) mInflater.inflate(
                        R.layout.list_item2, parent, false);


            }else{
                layout =(RelativeLayout) convertView;
            }
            Typeface bariol=Typeface.createFromAsset(getActivity().getAssets(), "fonts/ll.ttf");

            TextView title=(TextView) layout.findViewById(R.id.title);
            TextView desc = (TextView)layout.findViewById(R.id.desc);
            TextView joined = (TextView)layout.findViewById(R.id.joined);

            TextView timePassed = (TextView)layout.findViewById(R.id.timePassed);
            joined.setTypeface(bariol);
            desc.setTypeface(bariol);
            title.setTypeface(bariol);
            timePassed.setTypeface(bariol);


         //   ImageView profile=(ImageView) layout.findViewById(R.id.profile);
           // ImageView check=(ImageView) layout.findViewById(R.id.check);
            //have to get the bitmap of the profile user who posted/
            /*ParseObject entry = entries2.get(position);
            if (entry==null){
            Log.e("TAG","Entry"+ position+" is null");}

            else {

                if(entry.get("picture")==null)
                {
                    Log.e("TAG","picture"+ position+" is null");
                }else{
                    String encoded = entry.get("picture").toString();
                byte[] decodedString = Base64.decode(encoded, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                profile.setImageBitmap(decodedByte);}
                // this sets the green check to the view
                check.setImageResource(R.drawable.ok);
                //to be adjusted later
                //set title of post
                if(entry.get("name")==null){
                    Log.e("TAG","title"+ position+" is null");
             }else{
                    title.setText(entry.get("name").toString());
                }
                //set description of the post
                if(entry.getString("poster")==null){
                    Log.e("TAG","poster"+ position+" is null");
                }else{
                String description = "By " + entry.getString("poster");
                desc.setText(description);}

                //sets city and state in listview
                if(entry.getString("city")==null||entry.getString("state")==null){
                    Log.e("TAG","city"+ position+" is null");
                }else {
                    city.setText(entry.getString("city") + ", " + entry.getString("state"));
                    //
                }
            }*/


return layout;
        }
        public ParseObject getPosition(int position)
        {
            return entries2.get(position);
        }

    }



}