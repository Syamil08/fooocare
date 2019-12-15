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

    public static int getProteinLaki(int berat){
        int protein = 0;
        if(berat >= 55 && berat <= 60){
            protein = 55;
        }
        else if(berat >= 60 && berat <= 65){
            protein = 60;
        }
        else if(berat >= 65 && berat <= 70){
            protein = 65;
        }
        else if(berat >= 70 && berat <= 75){
            protein = 70;
        }
        else if(berat >= 75 && berat <= 80){
            protein = 75;
        }
        else if(berat >= 80 && berat <= 85){
            protein = 80;
        }
        else if(berat >= 85 && berat <= 90){
            protein = 85;
        }
        else if(berat >= 90){
            protein = 90;
        }
        return  protein;
    }

    public static int getProteinPerempuan(int berat){
        int protein = 0;
        if(berat >= 40 && berat <= 45){
            protein = 40;
        }
        else if(berat >= 45 && berat <= 50){
            protein = 45;
        }
        else if(berat >= 50 && berat <= 55){
            protein = 50;
        }
        else if(berat >= 55 && berat <= 60){
            protein = 55;
        }
        else if(berat >= 60 && berat <= 65){
            protein = 60;
        }
        else if(berat >= 65 && berat <= 70){
            protein = 65;
        }
        else if(berat >= 70 && berat <= 75){
            protein = 70;
        }
        else if(berat >= 75){
            protein = 75;
        }
        return  protein;
    }
}
