package com.example.fooocare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooocare.Model.MakananKarboGenerator;
import com.example.fooocare.Model.MakananKarbohidratModel;
import com.example.fooocare.Model.MakananModel;
import com.example.fooocare.Model.MakananProteinGenerator;
import com.example.fooocare.Model.MakananProteinModel;

import java.util.ArrayList;

public class JenisMakananActivity extends AppCompatActivity {
    private RecyclerView mRecyclerMakananPagi, mRecyclerMakananSiang,mRecyclerMakananMalam;
    private RecyclerView.Adapter mAdapterPagi, mAdapterSiang, mAdapterMalam;
    private RecyclerView.LayoutManager mLayoutManagerPagi, mLayoutManagerSiang,mLayoutManagerMalam;
    ArrayList<MakananModel> listMakanan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_makanan);
        GenerateMakananKarbo();
        GenerateMakananProtein();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            if(b.get("tag").equals("Menu Pagi")) {
                Toast.makeText(getApplicationContext(), "Menu pagi", Toast.LENGTH_SHORT).show();
                InitMakananPagi();
            }
            else if(b.get("tag").equals("Menu Siang")) {
                Toast.makeText(getApplicationContext(), "Menu siang", Toast.LENGTH_SHORT).show();
                InitMakananSiang();
            }else{
                Toast.makeText(getApplicationContext(), "Menu malam", Toast.LENGTH_SHORT).show();
                InitMakananMalam();
            }
        }
    }

    public void InitMakananPagi(){
        mRecyclerMakananPagi = findViewById(R.id.makanan);
        mLayoutManagerPagi = new LinearLayoutManager(this);
        mRecyclerMakananPagi.setLayoutManager(mLayoutManagerPagi);
        mAdapterPagi = new AgendaMakanPagiAdapter(this,listMakanan);
        mRecyclerMakananPagi.setAdapter(mAdapterPagi);
    }

    public void InitMakananSiang(){
        mRecyclerMakananSiang = findViewById(R.id.makanan);
        mLayoutManagerSiang = new LinearLayoutManager(getApplicationContext());
        mRecyclerMakananSiang.setLayoutManager(mLayoutManagerSiang);
        mAdapterSiang = new AgendaMakanSiangAdapter(this,listMakanan);
        mRecyclerMakananSiang.setAdapter(mAdapterSiang);
    }

    public void InitMakananMalam(){
        mRecyclerMakananMalam = findViewById(R.id.makanan);
        mLayoutManagerMalam = new LinearLayoutManager(this);
        mRecyclerMakananMalam.setLayoutManager(mLayoutManagerMalam);
        mAdapterMalam = new AgendaMakanMalamAdapter(this,listMakanan);
        mRecyclerMakananMalam.setAdapter(mAdapterMalam);
    }



    public void GenerateMakananKarbo(){
        ArrayList<MakananKarbohidratModel> karbos = new MakananKarboGenerator().getListMakanan();
        for (MakananKarbohidratModel karbo: karbos){
            listMakanan.add(karbo);
        }
    }

    public void GenerateMakananProtein(){
        ArrayList<MakananProteinModel> proteins = new MakananProteinGenerator().getListMakanan();
        for (MakananProteinModel protein: proteins){
            listMakanan.add(protein);

        }
    }
}


