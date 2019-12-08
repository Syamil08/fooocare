package com.example.fooocare.Model;

public class MakananProteinModel {
    private String nama;
    private float kalori, protein;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getKalori() {
        return kalori;
    }

    public void setKalori(float kalori) {
        this.kalori = kalori;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public MakananProteinModel(String nama, float kalori, float protein) {
        this.nama = nama;
        this.kalori = kalori;
        this.protein = protein;
    }
}
