package com.example.fooocare;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.fooocare.Model.Pengguna;
import com.example.fooocare.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;

public class SignUP extends AppCompatActivity implements Fragment_sign_up_data.OnHeadlineSelectedListener {
    SectionsPagerAdapter sectionsPagerAdapter;
    Button btnNext, btnPrev, btnFinish;
    int position = 0;
    DatabaseReference reff;
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
                    btnFinish.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }
            }
        });

        reff = FirebaseDatabase.getInstance().getReference().child("Pengguna");
        Pengguna pengguna = new Pengguna("Dimastyo Muhaimin Arifin",
                "dimas@gmail.com", "muhaimin123", 174, 70);
        reff.push().setValue(pengguna);

    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnFinish.setVisibility(View.VISIBLE);
    }

    String tag = "I";
    @Override
    public void someEvent(String s) {
        reff = FirebaseDatabase.getInstance().getReference().child("Test Fragment");
        reff.push().setValue(s);
        Log.d(tag, s);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }
}