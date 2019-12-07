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
        //18 -10 tahun Laki-Laki
        bmrList.add(new BMRModel(55,10, 18, 1625, "Laki-Laki"));
        bmrList.add(new BMRModel(60,10, 18, 1713, "Laki-Laki"));
        bmrList.add(new BMRModel(65,10, 18, 1801, "Laki-Laki"));
        bmrList.add(new BMRModel(70,10, 18, 1889, "Laki-Laki"));
        bmrList.add(new BMRModel(75,10, 18, 1977, "Laki-Laki"));
        bmrList.add(new BMRModel(80,10, 18, 2065, "Laki-Laki"));
        bmrList.add(new BMRModel(85,10, 18, 2242, "Laki-Laki"));
        //18-30 tahun laki-laki
        bmrList.add(new BMRModel(55,18, 30, 1514, "Laki-Laki"));
        bmrList.add(new BMRModel(60,18, 30, 1589, "Laki-Laki"));
        bmrList.add(new BMRModel(65,18, 30, 1664, "Laki-Laki"));
        bmrList.add(new BMRModel(70,18, 30, 1739, "Laki-Laki"));
        bmrList.add(new BMRModel(75,18, 30, 1814, "Laki-Laki"));
        bmrList.add(new BMRModel(80,18, 30, 1964, "Laki-Laki"));
        bmrList.add(new BMRModel(85,18, 30, 2039, "Laki-Laki"));
        //4-60 tahun laki-laki
        bmrList.add(new BMRModel(55,18, 30, 1499, "Laki-Laki"));
        bmrList.add(new BMRModel(60,18, 30, 1556, "Laki-Laki"));
        bmrList.add(new BMRModel(65,18, 30, 1613, "Laki-Laki"));
        bmrList.add(new BMRModel(70,18, 30, 1670, "Laki-Laki"));
        bmrList.add(new BMRModel(75,18, 30, 1727, "Laki-Laki"));
        bmrList.add(new BMRModel(80,18, 30, 1785, "Laki-Laki"));
        bmrList.add(new BMRModel(85,18, 30, 1842, "Laki-Laki"));

        //18 -10 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1224, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1291, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1424, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1491, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1557, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1624, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1691, "Perempuan"));
        //18-30 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1075, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1149, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1223, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1296, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1370, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1444, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1518, "Perempuan"));
        //4-60 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1167, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1207, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1248, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1288, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1329, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1369, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1410, "Perempuan"));

        CaloryCounter.bmrList =this.bmrList;
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