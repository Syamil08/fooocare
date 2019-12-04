package com.example.fooocare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.fooocare.Model.Pengguna;
import com.example.fooocare.ui.main.SectionsPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SignUP extends AppCompatActivity implements Fragment_sign_up_data.OnHeadlineSelectedListener, Fragment_sign_up_tinggi_badan.FragmentSignUpTinggiBadanListener, Fragment_sign_up_data.MovePositionListener
{
    SectionsPagerAdapter sectionsPagerAdapter;
    Button btnNext, btnPrev, btnFinish;
    int position = 0;
    DatabaseReference reff;
    Pengguna pengguna;
    FirebaseAuth fAuth;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (restorePrefData()) {
//
//            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
//
//        }

        setContentView(R.layout.activity_sign_up);
//        btnNext = (Button) findViewById(R.id.btn_next);
//        btnPrev = (Button) findViewById(R.id.btn_prev);
//        btnFinish = (Button) findViewById(R.id.btn_finish);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.screen_viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tab_indicator);
        tabs.setupWithViewPager(viewPager);
        pengguna = new Pengguna();
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                position = viewPager.getCurrentItem();
//                if (position < 3) {
//
//                    position++;
//                    viewPager.setCurrentItem(position);
//
//                }
//
////                ketika posisi sudah sampai pada banyaknya data maka
//
//                if (position == 2) {
//
////                    TODO: munculkan tombol GetStarted kemudian hilangkan tabLayout dan tombol Next
//
//                    loadLastScreen();
//
//                }
//            }
//        });

//        btnPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                position = viewPager.getCurrentItem();
//                if (position < 3) {
//                    position--;
//                    viewPager.setCurrentItem(position);
//                    btnFinish.setVisibility(View.INVISIBLE);
//                    btnNext.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//
//        btnFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                position = viewPager.getCurrentItem();
//                Log.d("Nama Pengguna", pengguna.getNamaLengkap());
//                Log.d("Tinggi Badan Pengguna", String.valueOf(pengguna.getTinggiBadan()));
//
////                reff = FirebaseDatabase.getInstance().getReference().child("Pengguna");
//                fAuth = FirebaseAuth.getInstance();
////                reff.push().setValue(pengguna);
//
//                fAuth.createUserWithEmailAndPassword(pengguna.getEmail(), pengguna.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(SignUP.this, "User created",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
////                            savePrefsData();
//                        }
//                        else {
//                            Toast.makeText(SignUP.this, "Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });

    }

    //Load last screen

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
        pengguna.setJenis_kelamin(s.get(4));
    }

    @Override
    public void fragmentSignUpTinggiBadanEvent(List<Integer> s) {
        pengguna.setTinggiBadan(s.get(0));
        pengguna.setBeratBadan(s.get(1));
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;

    }

    @Override
    public void move(int position) {
        viewPager.setCurrentItem(position);
    }
}