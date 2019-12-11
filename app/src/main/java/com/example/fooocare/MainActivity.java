package com.example.fooocare;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooocare.Model.BMRModel;
import com.example.fooocare.Model.CaloryCounter;
import com.example.fooocare.Model.Pengguna;

import java.util.ArrayList;

//    Halaman Welcome Screen Logic

public class MainActivity extends AppCompatActivity {
    ArrayList<BMRModel> bmrList;
    private TextView tv_head, tv_section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmrList = new ArrayList<BMRModel>();

        tv_head = (TextView) findViewById(R.id.tv_head);
        tv_section = (TextView) findViewById(R.id.tv_section);
//        Memanggil library animation
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);

//        CaloryCounter.bmrList =this.bmrList;
        tv_head.startAnimation(myanim);
        tv_section.startAnimation(myanim);
        final Intent onBoarding = new Intent(this, onBoarding.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(onBoarding);
                    finish();
                }
            }
        };

        timer.start();
    }
}



/*
    1. Topik / Masalah yang Akan diselesaikan
      - Mengatur pola makan bagi atlet sepakbola agar memiliki gizi yang seimbang
       serta agar bisa mendapatkan stamina terbaik ketika akan melakukan suatu pertandingan.
    2. Mengapa aplikasi tersebut harus dibangun di Android
      - User experience (Pengalaman dari menggunakan aplikasi yang diberikan kepada pengguna yang belum tentu didapatkan
        pada sistem berbasis web atau yang lainnya).
      - Android memiliki akses kedalam sumber daya sistemnya (seperti kamera, baterai, dll).
    3. Siapa usernya
       Pemain Sepakbola
    4. Siapa clientnya
       - Akademi Sepakbola
       - Klub Sepakbola
       - Tim kesehatan dalam sebuah klub sepakbola
    5. Apa inputnya
       - nama
       - password
       - email
       - jenis kelamin
       - Usia Pemain
       - Berat badan pemain
       - Tinggi badan pemain
       - Jadwal pertandingan pemain
       - Kesibukan Pemain
    6. Apa outputnya
        Rekomendasi menu makanan
    7. Fungsi-fungsi/fitur apa saja yang ada pada aplikasi tersebut
        - fitur rekomendasi makanan
        - fitur tambah pertandingan
        - fitur check-out makanan

 */