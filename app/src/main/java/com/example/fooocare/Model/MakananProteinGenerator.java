package com.example.fooocare.Model;

import java.util.ArrayList;

public class MakananProteinGenerator {
    private ArrayList<MakananProteinModel> listMakanan = new ArrayList<>();

    public MakananProteinGenerator() {
        listMakanan.add(new MakananProteinModel("Ayam Goreng", (float)260, (float) 21.93,"https://selerasa.com/wp-content/uploads/2015/12/images_daging_ayam-goreng.jpg"));
        listMakanan.add(new MakananProteinModel("Daging Sapi", (float)260, (float) 21.93,"https://selerasa.com/wp-content/uploads/2015/12/images_daging_ayam-goreng.jpg"));
        listMakanan.add(new MakananProteinModel("Telur Dadar", (float)260, (float) 6.48,"https://selerasa.com/wp-content/uploads/2015/12/images_daging_ayam-goreng.jpg"));
        listMakanan.add(new MakananProteinModel("Ikan Mujair Goreng", (float)169, (float) 24.5,"https://selerasa.com/wp-content/uploads/2015/12/images_daging_ayam-goreng.jpg"));

    }

    public ArrayList<MakananProteinModel> getListMakanan() {
        return listMakanan;
    }
}
