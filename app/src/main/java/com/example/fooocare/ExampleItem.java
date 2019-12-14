package com.example.fooocare;

import java.util.ArrayList;

public class ExampleItem {
    private  String judul;
    private  String tanggal;
    private  int position;

    public ExampleItem(String judul, String tanggal, int position) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ExampleItem(String judul, String tanggal) {
        this.judul = judul;
        this.tanggal = tanggal;
    }


    public ExampleItem(ArrayList<String> list){
        this.judul = list.get(0);
        this.tanggal = list.get(1);
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public String toString(){
        return judul;
    }
}
