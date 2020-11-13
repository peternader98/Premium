package com.example.premium.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.premium.AddFriend;
import com.example.premium.Contacts;
import com.example.premium.R;
import com.example.premium.Setting;
import com.example.premium.Story;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //return PlaceholderFragment.newInstance(position);
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = Contacts.newInstance("Peter ","Nader");
                break;
            case 1:
                fragment = Story.newInstance("Nader ","Mounir");
                break;
            case 2:
                fragment = AddFriend.newInstance("Mounir ","Ramzy");
                break;
            case 3:
                fragment = Setting.newInstance("Ramzy ","Gerges");
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Contacts";
            case 1:
                return "Story";
            case 2:
                return "Add friend";
            case 3:
                return "Setting";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}