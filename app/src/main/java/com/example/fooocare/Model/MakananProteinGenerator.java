package com.example.fooocare.Model;

import java.util.ArrayList;

public class MakananProteinGenerator {
    private ArrayList<MakananProteinModel> listMakanan = new ArrayList<>();

    public MakananProteinGenerator() {
        listMakanan.add(new MakananProteinModel("Ayam Goreng", (float)260, (float) 21.93));
        listMakanan.add(new MakananProteinModel("Daging Sapi", (float)260, (float) 21.93));
        listMakanan.add(new MakananProteinModel("Telur Dadar", (float)260, (float) 6.48));
        listMakanan.add(new MakananProteinModel("Ikan Mujair Goreng", (float)169, (float) 24.5));

    }

    public ArrayList<MakananProteinModel> getListMakanan() {
        return listMakanan;
    }
}
