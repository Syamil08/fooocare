package com.example.fooocare.Model;

public class MakananKarbohidratModel {
    private String nama;
    private float karbohidrat, kalori;

    public MakananKarbohidratModel(String nama, float karbohidrat, float kalori) {
        this.nama = nama;
        this.karbohidrat = karbohidrat;
        this.kalori = kalori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getKarbohidrat() {
        return karbohidrat;
    }

    public void setKarbohidrat(float karbohidrat) {
        this.karbohidrat = karbohidrat;
    }

    public float getKalori() {
        return kalori;
    }

    public void setKalori(float kalori) {
        this.kalori = kalori;
    }
}
