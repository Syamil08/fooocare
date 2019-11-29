package com.example.fooocare.Model;

import java.util.List;

public class Pengguna {
    private String namaLengkap, email, password;
    private int tinggiBadan, beratBadan;
//    private List<String> olahraga;

    public Pengguna(String namaLengkap, String email, String password, int tinggiBadan, int beratBadan) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.tinggiBadan = tinggiBadan;
        this.beratBadan = beratBadan;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(int tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }
}
