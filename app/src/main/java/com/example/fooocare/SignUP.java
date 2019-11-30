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
import java.util.List;

public class SignUP extends AppCompatActivity implements Fragment_sign_up_data.OnHeadlineSelectedListener, Fragment_sign_up_tinggi_badan.FragmentSignUpTinggiBadanListener {
    SectionsPagerAdapter sectionsPagerAdapter;
    Button btnNext, btnPrev, btnFinish;
    int position = 0;
    DatabaseReference reff;
    Pengguna pengguna;
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
        pengguna = new Pengguna();
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
//        final Pengguna pengguna = new Pengguna("Dimastyo Muhaimin Arifin",
//                "dimas@gmail.com", "muhaimin123", 174, 70);
//        reff.push().setValue(pengguna);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                position = viewPager.getCurrentItem();
                Log.d("Nama Pengguna", pengguna.getNamaLengkap());
                Log.d("Tinggi Badan Pengguna", String.valueOf(pengguna.getTinggiBadan()));
            }
        });

    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnFinish.setVisibility(View.VISIBLE);
    }

    String tag = "I";


    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void fragmentSignUpEvent(List<String> s) {
        pengguna.setNamaLengkap(s.get(0));
        pengguna.setEmail(s.get(1));
        pengguna.setPassword(s.get(2));
        pengguna.setUsia(Integer.parseInt(s.get(3)));
//        pengguna.setJenis_kelamin(s.get(4));
    }

    @Override
    public void fragmentSignUpTinggiBadanEvent(List<Integer> s) {
        pengguna.setTinggiBadan(s.get(0));
        pengguna.setBeratBadan(s.get(1));
    }
}