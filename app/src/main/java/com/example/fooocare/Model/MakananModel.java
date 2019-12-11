package com.example.fooocare.Model;

public class MakananModel {
    protected String nama;
    protected float kalori;
    protected String images;

    public MakananModel(String nama, float kalori, String images) {
        this.nama = nama;
        this.kalori = kalori;
        this.images = images;
    }

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
