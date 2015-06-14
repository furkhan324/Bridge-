package com.example.mohammed.bridge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs;
    // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    Context context;
    private int[] imageResId = {
            R.drawable.home,
            R.drawable.search
    };

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb,Context context) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.context=context;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Home3 home = new Home3();
            return home;
        }
        else if(position == 1)         // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Tab1 tab2 = new Tab1();
            return tab2;
        }
        else      // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Tab1 tab2 = new Tab1();
            return tab2;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip



    // This method return the Number of tabs for the tabs Strip
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];
        Drawable image = context.getResources().getDrawable(imageResId[position]);


            image.setBounds(0, 0,42, 42);


        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}