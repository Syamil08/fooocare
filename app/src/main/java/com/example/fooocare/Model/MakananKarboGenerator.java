package com.example.fooocare.Model;

import java.util.ArrayList;

public class MakananKarboGenerator {
    private ArrayList<MakananKarbohidratModel> listMakanan = new ArrayList<>();

    public MakananKarboGenerator() {
        listMakanan.add(new MakananKarbohidratModel("Nasi Putih", (float)44.08, 204));
        listMakanan.add(new MakananKarbohidratModel("Roti Tawar", (float)47, 200));
        listMakanan.add(new MakananKarbohidratModel("Kentang Rebus", (float)27.38, 118));
        listMakanan.add(new MakananKarbohidratModel("Spageti", (float)42.95, 220));
        listMakanan.add(new MakananKarbohidratModel("Bubur Ayam", (float)42.95, 220));
        listMakanan.add(new MakananKarbohidratModel("Mie", (float)40.02, 219));
    }

    public ArrayList<MakananKarbohidratModel> getListMakanan() {
        return listMakanan;
    }
}
