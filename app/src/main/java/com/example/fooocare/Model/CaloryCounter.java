package com.example.fooocare.Model;

import java.util.ArrayList;

public class CaloryCounter {
    public static ArrayList<BMRModel> bmrList;
    public static float coutnCalory(float berat_badan, float usia, String jenis_kelamin, float tinggi_badan, float aktivitas){
        float bmr = 0;
        for (BMRModel data: bmrList) {
            if(data.getBerat_badan() <= berat_badan && data.getUsia_bawah() < usia && data.getUsia_atas() >= usia && data.getJenis_kelamin() == jenis_kelamin){
                bmr = data.getEnergi();
            }
        }

        float imt = berat_badan / (tinggi_badan * tinggi_badan);
        float bmr_sda = bmr + (float)(0.1 * bmr);
        float aktivitas_kal = aktivitas * bmr_sda;

    return aktivitas_kal;
    }
}
