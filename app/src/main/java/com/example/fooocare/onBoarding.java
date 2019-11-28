package com.example.fooocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class onBoarding extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

//        fill list screen

        List<Screenitem> mList = new ArrayList<>();
        mList.add(new Screenitem("Menjaga Kesehatan","Mencegah lebih baik daripada mengobati, jaga kesehatan keluarga Anda saat ini",R.drawable.vektor_one));
        mList.add(new Screenitem("Menjaga Stamina","Memudahkan anda untuk melihat report harian yang anda miliki",R.drawable.vektor_three));
        mList.add(new Screenitem("Report Harian","Ambil gambar anda dan lihat kalorinya",R.drawable.vektor_two));

//        setup viewpager

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);
    }
}
