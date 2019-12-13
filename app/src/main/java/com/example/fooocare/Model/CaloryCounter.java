package com.example.fooocare.Model;

import android.util.Log;

import java.util.ArrayList;

public class CaloryCounter {
    public static ArrayList<BMRModel> bmrList = new ArrayList<>();

    public CaloryCounter() {
        GenerateBMR();
        GeneratePengali();
    }

    public static float coutnCalory(float berat_badan, float usia, String jenis_kelamin, float tinggi_badan, float aktivitas) {

        float bmr = 0;
        for (BMRModel data : bmrList) {
            if (data.getBerat_badan() <= berat_badan && data.getUsia_bawah() < usia && data.getUsia_atas() >= usia && data.getJenis_kelamin().equals(jenis_kelamin)) {
                bmr = data.getEnergi();
            }
        }

        float imt = berat_badan / (tinggi_badan * tinggi_badan);
        float bmr_sda = bmr + (float) (0.1 * bmr);
        float aktivitas_kal = aktivitas * bmr_sda;

        return aktivitas_kal;
    }

    public float hitungKalori(float berat_badan, float usia, String jenis_kelamin, float tinggi_badan, float aktivitas) {

        float bmr = 0;
        for (BMRModel data : bmrList) {
            if (data.getBerat_badan() <= berat_badan && data.getUsia_bawah() < usia && data.getUsia_atas() >= usia && data.getJenis_kelamin() == jenis_kelamin) {
                bmr = data.getEnergi();
            }
        }

        float imt = berat_badan / (tinggi_badan * tinggi_badan);
        float bmr_sda = bmr + (float) (0.1 * bmr);
        float aktivitas_kal = aktivitas * bmr_sda;

        return aktivitas_kal;
    }

    public static ArrayList<Sepakbola> mdlSepakbola = new ArrayList<>();

    public static float agendaCounter(int berat, int latihan) {
        float _pengali = 0, total = 0;
        for (Sepakbola data : mdlSepakbola) {
            Log.d("ListModel", String.valueOf(data.getPengali()));
            if (data.getBerat() >= berat) {
                _pengali = data.getPengali();
                break;
            }
        }
        total = latihan * 90 * _pengali;
        Log.d("total", String.valueOf(total));
        return total;
    }

    public static void GenerateBMR() {
        //18 -10 tahun Laki-Laki
        bmrList.add(new BMRModel(55, 10, 18, 1625, "Laki-Laki"));
        bmrList.add(new BMRModel(60, 10, 18, 1713, "Laki-Laki"));
        bmrList.add(new BMRModel(65, 10, 18, 1801, "Laki-Laki"));
        bmrList.add(new BMRModel(70, 10, 18, 1889, "Laki-Laki"));
        bmrList.add(new BMRModel(75, 10, 18, 1977, "Laki-Laki"));
        bmrList.add(new BMRModel(80, 10, 18, 2065, "Laki-Laki"));
        bmrList.add(new BMRModel(85, 10, 18, 2242, "Laki-Laki"));
        //18-30 tahun laki-laki
        bmrList.add(new BMRModel(55, 18, 30, 1514, "Laki-Laki"));
        bmrList.add(new BMRModel(60, 18, 30, 1589, "Laki-Laki"));
        bmrList.add(new BMRModel(65, 18, 30, 1664, "Laki-Laki"));
        bmrList.add(new BMRModel(70, 18, 30, 1739, "Laki-Laki"));
        bmrList.add(new BMRModel(75, 18, 30, 1814, "Laki-Laki"));
        bmrList.add(new BMRModel(80, 18, 30, 1964, "Laki-Laki"));
        bmrList.add(new BMRModel(85, 18, 30, 2039, "Laki-Laki"));
        //4-60 tahun laki-laki
        bmrList.add(new BMRModel(55, 18, 30, 1499, "Laki-Laki"));
        bmrList.add(new BMRModel(60, 18, 30, 1556, "Laki-Laki"));
        bmrList.add(new BMRModel(65, 18, 30, 1613, "Laki-Laki"));
        bmrList.add(new BMRModel(70, 18, 30, 1670, "Laki-Laki"));
        bmrList.add(new BMRModel(75, 18, 30, 1727, "Laki-Laki"));
        bmrList.add(new BMRModel(80, 18, 30, 1785, "Laki-Laki"));
        bmrList.add(new BMRModel(85, 18, 30, 1842, "Laki-Laki"));

        //18 -10 tahun Perempuan
        bmrList.add(new BMRModel(40, 10, 18, 1224, "Perempuan"));
        bmrList.add(new BMRModel(45, 10, 18, 1291, "Perempuan"));
        bmrList.add(new BMRModel(50, 10, 18, 1424, "Perempuan"));
        bmrList.add(new BMRModel(55, 10, 18, 1491, "Perempuan"));
        bmrList.add(new BMRModel(60, 10, 18, 1557, "Perempuan"));
        bmrList.add(new BMRModel(65, 10, 18, 1624, "Perempuan"));
        bmrList.add(new BMRModel(70, 10, 18, 1691, "Perempuan"));
        //18-30 tahun Perempuan
        bmrList.add(new BMRModel(40, 10, 18, 1075, "Perempuan"));
        bmrList.add(new BMRModel(45, 10, 18, 1149, "Perempuan"));
        bmrList.add(new BMRModel(50, 10, 18, 1223, "Perempuan"));
        bmrList.add(new BMRModel(55, 10, 18, 1296, "Perempuan"));
        bmrList.add(new BMRModel(60, 10, 18, 1370, "Perempuan"));
        bmrList.add(new BMRModel(65, 10, 18, 1444, "Perempuan"));
        bmrList.add(new BMRModel(70, 10, 18, 1518, "Perempuan"));
        //4-60 tahun Perempuan
        bmrList.add(new BMRModel(40, 10, 18, 1167, "Perempuan"));
        bmrList.add(new BMRModel(45, 10, 18, 1207, "Perempuan"));
        bmrList.add(new BMRModel(50, 10, 18, 1248, "Perempuan"));
        bmrList.add(new BMRModel(55, 10, 18, 1288, "Perempuan"));
        bmrList.add(new BMRModel(60, 10, 18, 1329, "Perempuan"));
        bmrList.add(new BMRModel(65, 10, 18, 1369, "Perempuan"));
        bmrList.add(new BMRModel(70, 10, 18, 1410, "Perempuan"));

    }

    public static void GeneratePengali() {

        ArrayList<Sepakbola> sepakbola = new ArrayList<>();

        mdlSepakbola.add(new Sepakbola(50, 7));
        mdlSepakbola.add(new Sepakbola(60, 8));
        mdlSepakbola.add(new Sepakbola(70, 9));
        mdlSepakbola.add(new Sepakbola(80, 10));
        mdlSepakbola.add(new Sepakbola(90, 12));

    }

}
