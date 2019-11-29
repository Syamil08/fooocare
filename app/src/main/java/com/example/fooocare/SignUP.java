package com.example.fooocare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fooocare.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SignUP extends AppCompatActivity {
    SectionsPagerAdapter sectionsPagerAdapter;
    Button btnNext, btnPrev, btnFinish;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPrev = (Button) findViewById(R.id.btn_prev);
        btnFinish = (Button) findViewById(R.id.btn_finish);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.screen_viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tab_indicator);
        tabs.setupWithViewPager(viewPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                position = viewPager.getCurrentItem();
                if (position < 3) {

                    position++;
                    viewPager.setCurrentItem(position);

                }

//                ketika posisi sudah sampai pada banyaknya data maka

                if (position == 2) {

//                    TODO: munculkan tombol GetStarted kemudian hilangkan tabLayout dan tombol Next

                    loadLastScreen();

                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                position = viewPager.getCurrentItem();
                if (position < 3) {

                    position--;
                    viewPager.setCurrentItem(position);

                }

                if (position == 0) {
                    loadFirstScreen();

                }
            }
        });
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnFinish.setVisibility(View.VISIBLE);
    }

    private void loadFirstScreen() {
        btnPrev.setVisibility(View.INVISIBLE);
    }
}