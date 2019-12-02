package com.example.fooocare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

//        set untuk home fragment yang akan muncul pertama kali
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


//        ArrayList<ExampleItem> exampleList = new ArrayList<>();
//        exampleList.add(new ExampleItem("Line 1","Line 2"));
//        exampleList.add(new ExampleItem("Line 3","Line 4"));
//        exampleList.add(new ExampleItem("Line 5","Line 6"));
//
//        mRecyclerView = findViewById(R.id.recyclerViewAgenda);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mAdapter = new ExampleAdapter(exampleList);
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    if (menuItem.getItemId() == R.id.nav_home){
                        selectedFragment = new HomeFragment();
                    }

                    if (menuItem.getItemId() == R.id.nav_agenda){
                        selectedFragment = new AgendaFragment();
                    }

                    if (menuItem.getItemId() == R.id.nav_statistic){
                        selectedFragment = new StatistikFragment();
                    }

                    if (menuItem.getItemId() == R.id.nav_profil){
                        selectedFragment = new ProfilFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                    return true;
                }
            };


}
