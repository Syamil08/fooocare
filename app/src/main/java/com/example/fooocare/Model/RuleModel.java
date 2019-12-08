package com.example.fooocare.Model;

public class RuleModel {
    String jenis_kelamin;
    int beratBadan,umurbawah,umurAtas,energi;

    public RuleModel(String jenis_kelamin, int beratBadan, int umurbawah, int umurAtas, int energi) {
        this.jenis_kelamin = jenis_kelamin;
        this.beratBadan = beratBadan;
        this.umurbawah = umurbawah;
        this.umurAtas = umurAtas;
        this.energi = energi;
    }



    public String getJenis_kelamin() {
        return jenis_kelamin;

    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }

    public int getUmurbawah() {
        return umurbawah;
    }

    public void setUmurbawah(int umurbawah) {
        this.umurbawah = umurbawah;
    }

    public int getUmurAtas() {
        return umurAtas;
    }

    public void setUmurAtas(int umurAtas) {
        this.umurAtas = umurAtas;
    }

    public int getEnergi() {
        return energi;
    }

    public void setEnergi(int energi) {
        this.energi = energi;
    }
}

