package com.example.fooocare;

public class ExampleItem {
    private  String judul;
    private  String tanggal;

    public ExampleItem(String judul, String tanggal) {
        this.judul = judul;
        this.tanggal = tanggal;
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
}
