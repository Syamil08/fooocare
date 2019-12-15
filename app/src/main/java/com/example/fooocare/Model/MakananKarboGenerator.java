package com.example.fooocare.Model;

import java.util.ArrayList;

public class MakananKarboGenerator {
    private ArrayList<MakananKarbohidratModel> listMakanan = new ArrayList<>();

    public MakananKarboGenerator() {
        listMakanan.add(new MakananKarbohidratModel("Nasi Putih", (float)44.08, 204,"https://awsimages.detik.net.id/visual/2019/07/09/5eb5d75b-7eae-4e9c-8a94-1b3a536891ec_169.jpeg?w=650"));
        listMakanan.add(new MakananKarbohidratModel("Roti Tawar", (float)47.00987, 200,"https://awsimages.detik.net.id/visual/2019/07/09/5eb5d75b-7eae-4e9c-8a94-1b3a536891ec_169.jpeg?w=650"));
        listMakanan.add(new MakananKarbohidratModel("Kentang Rebus", (float)27.38, 118,"https://awsimages.detik.net.id/visual/2019/07/09/5eb5d75b-7eae-4e9c-8a94-1b3a536891ec_169.jpeg?w=650"));
        listMakanan.add(new MakananKarbohidratModel("Spageti", (float)42.95, 220,"https://awsimages.detik.net.id/visual/2019/07/09/5eb5d75b-7eae-4e9c-8a94-1b3a536891ec_169.jpeg?w=650"));
        listMakanan.add(new MakananKarbohidratModel("Bubur Ayam", (float)42.95, 220,"https://awsimages.detik.net.id/visual/2019/07/09/5eb5d75b-7eae-4e9c-8a94-1b3a536891ec_169.jpeg?w=650"));
        listMakanan.add(new MakananKarbohidratModel("Mie", (float)40.02, 219,"https://awsimages.detik.net.id/visual/2019/07/09/5eb5d75b-7eae-4e9c-8a94-1b3a536891ec_169.jpeg?w=650"));
    }

    public ArrayList<MakananKarbohidratModel> getListMakanan() {
        return listMakanan;
    }
}
