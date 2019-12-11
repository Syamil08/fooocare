package com.example.fooocare.Model;

public class MakananProteinModel extends MakananModel{
    private float protein;

    public MakananProteinModel(String nama, float kalori, float protein, String images) {
        super(nama,kalori,images);
        this.protein = protein;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }
}
