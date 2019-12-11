package com.example.fooocare.Model;

public class MakananKarbohidratModel extends MakananModel {
    private float karbohidrat;

    public MakananKarbohidratModel(String nama, float karbohidrat, float kalori, String images) {
        super(nama,kalori,images);
        this.karbohidrat = karbohidrat;
    }

    public float getKarbohidrat() {
        return karbohidrat;
    }

    public void setKarbohidrat(float karbohidrat) {
        this.karbohidrat = karbohidrat;
    }

}
