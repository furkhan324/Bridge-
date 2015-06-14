package com.example.mohammed.bridge;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by mohammed on 5/17/15.
 */
public class Home3 extends Fragment {



    private String currentUserId;
    private ArrayAdapter<String> namesArrayAdapter;
    private ArrayList<String> names;
    private ListView usersListView;
    private Button logoutButton;
    private ProgressDialog progressDialog;
    private BroadcastReceiver receiver = null;
    private List<ParseUser> users;
    private userListAdapter listAdapter;
    private static final String[] ALPHABET ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private ArrayList<String> alphabet= new ArrayList<String>();
    TextView logo;

    public void init(){

        for(int i=0;i<ALPHABET.length;i++){
            alphabet.add(i,ALPHABET[i]);
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_home,container,false);
        init();



        /////////////////done setting interface///////////////////////


        currentUserId = ParseUser.getCurrentUser().getObjectId();
        names = new ArrayList<String>();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        users =new ArrayList<ParseUser>();
//don't include yourself
        query.whereNotEqualTo("objectId", currentUserId);

        try{
            users=query.find();}
        catch(Exception e){}
        listAdapter =new userListAdapter(users);

        ListView list = (ListView) v.findViewById(R.id.listView2);
        ImageView add= (ImageView) v.findViewById(R.id.add);
        list.setAdapter(listAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent("ADD");
                startActivity(i);
            }
        });

        /////////////////////////onClickoftheuserlist//////////////////////
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


            }
        });
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
        List<ParseUser> users2;

        public userListAdapter(List<ParseUser> users){

            users2=users;

        }
        public int getCount() {
            // TODO Auto-generated method stub
            return users2.size();
        }

        @Override
        public ParseUser getItem(int arg0) {
            // TODO Auto-generated method stub
            return users2.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

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
            TextView cd = (TextView)arg1.findViewById(R.id.textView6);
            ImageView iconImage=(ImageView) arg1.findViewById(R.id.imageView1);




            chapterName.setTypeface(bariol);
            cd.setTypeface(bariol);
            chapterDesc.setTypeface(bariol);
            icon.setTypeface(bariol);
            ParseUser chapter = users2.get(arg0);


            icon.setText(chapter.getUsername().toString().substring(0, 1).toUpperCase());
            chapterName.setText("Test Volunteering Opp");
            chapterDesc.setText("Testing beta succesfull");
            int x=arg0;







            return arg1;
        }

        public ParseUser getPosition(int position)
        {
            return users2.get(position);
        }

    }



}