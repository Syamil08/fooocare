package com.example.fooocare.Model;

import java.util.List;

public class Pengguna {
    private String namaLengkap, email, password;
    private int tinggiBadan, beratBadan;
    private List<String> olahraga;

    public Pengguna(String namaLengkap, String email, String password, int tinggiBadan, int beratBadan) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.tinggiBadan = tinggiBadan;
        this.beratBadan = beratBadan;
    }
}
