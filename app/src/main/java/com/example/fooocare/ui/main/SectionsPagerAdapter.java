package com.example.fooocare.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fooocare.Fragment_sign_up_olahraga;
import com.example.fooocare.Fragment_sign_up_data;
import com.example.fooocare.Fragment_sign_up_tinggi_badan;
import com.example.fooocare.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Fragment_sign_up_data();
                break;
            case 1:
                fragment = new Fragment_sign_up_tinggi_badan();
                break;
            case 2:
                fragment = new Fragment_sign_up_olahraga();
                break;
        }
        return fragment;
    }

//    @Nullable
//    @Override
////    public CharSequence getPageTitle(int position) {
////        return mContext.getResources().getString(position);
////    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}