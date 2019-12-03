package com.example.fooocare;

public class AgendaItem {
    public String pertandingan, tanggal;
    public boolean expanded;

    public AgendaItem(String pertandingan, String tanggal) {
        this.pertandingan = pertandingan;
        this.tanggal = tanggal;
        this.expanded = false;
    }

    public String getPertandingan() {
        return pertandingan;
    }

    public void setPertandingan(String pertandingan) {
        this.pertandingan = pertandingan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
