package com.example.fooocare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class onBoarding extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        sebelum aplikasi di launch maka kita lakukan pengecekan bahwa aplikasi telah dibuka atau belum

        if (restorePrefData()) {

            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();

        }


        setContentView(R.layout.activity_on_boarding);

//        ini views untuk tabLayout
        btnNext = (Button) findViewById(R.id.btn_next);
        btnGetStarted = (Button) findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.getstarted_animation);


//        fill list screen

        final List<Screenitem> mList = new ArrayList<>();
        mList.add(new Screenitem("Menjaga Kesehatan", "Mencegah lebih baik daripada mengobati, jaga kesehatan keluarga Anda saat ini", R.drawable.vektor_one));
        mList.add(new Screenitem("Menjaga Stamina", "Memudahkan anda untuk melihat report harian yang anda miliki", R.drawable.vektor_three));
        mList.add(new Screenitem("Report Harian", "Ambil gambar anda dan lihat kalorinya", R.drawable.vektor_two));

//        setup viewpager

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

//        setting tabLayout dengan viewPager

        tabIndicator.setupWithViewPager(screenPager);


//        btnNext Listener

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);

                }

//                ketika posisi sudah sampai pada banyaknya data maka

                if (position == mList.size() - 1) {

//                    TODO: munculkan tombol GetStarted kemudian hilangkan tabLayout dan tombol Next

                    loadLastScreen();

                }
            }
        });


//        tabLayout add change Listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() - 1) {

                    loadLastScreen();

                }

                if (tab.getPosition() < mList.size() - 1) {

                    btnGetStarted.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    tabIndicator.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Ketika Melakukan interaksi dengan btn Get Started maka akan diarahkan ke halaman login
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);

//                menyimpan nilai boolean dalam penyimpanan agar ketika user menjalankan aplikasi
//                kita tahu dia sudah melewati halaman onBoarding atau belum
//                dengan cara menggunakan shared preferences

                savePrefsData();
                finish();


            }
        });
    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;


    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }

    //    prosedur untuk memunculkan GetStarted dan menghilangkan tabLayout dan Button Next
    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

//        setting animasi
        btnGetStarted.setAnimation(btnAnim);

    }
}