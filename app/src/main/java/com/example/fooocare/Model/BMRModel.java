package com.example.fooocare.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BMRModel {
    private int berat_badan, usia_bawah, usia_atas, energi;
    private String jenis_kelamin;
    static BMRModel model;
    static BMRModel model2;

    public BMRModel(int berat_badan, int usia_bawah, int usia_atas, int energi, String jenis_kelamin) {
        this.berat_badan = berat_badan;
        this.usia_bawah = usia_bawah;
        this.usia_atas = usia_atas;
        this.energi = energi;
        this.jenis_kelamin = jenis_kelamin;
    }

    public int getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(int berat_badan) {
        this.berat_badan = berat_badan;
    }

    public int getUsia_bawah() {
        return usia_bawah;
    }

    public void setUsia_bawah(int usia_bawah) {
        this.usia_bawah = usia_bawah;
    }

    public int getUsia_atas() {
        return usia_atas;
    }

    public void setUsia_atas(int usia_atas) {
        this.usia_atas = usia_atas;
    }

    public int getEnergi() {
        return energi;
    }

    public void setEnergi(int energi) {
        this.energi = energi;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public static  void generateList(){
        DatabaseReference root  = FirebaseDatabase.getInstance().getReference().child("BMR");


        model = new BMRModel(55,10, 18, 1625, "Laki-Laki");
        model2 = new BMRModel(60,10, 18, 1713, "Laki-Laki");
        root.child("Bmr").push().setValue(model);
        root.child("Bmr").push().setValue(model2);
    }
}
