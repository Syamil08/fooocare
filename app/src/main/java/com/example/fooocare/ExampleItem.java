package com.example.fooocare;

import com.example.fooocare.Model.MakananModel;

import java.util.ArrayList;

public class ExampleItem {
    private  String judul;

    public ExampleItem(String judul, String tanggal, ArrayList<MakananModel> menuPagi, ArrayList<MakananModel> menuSiang, ArrayList<MakananModel> menuMalam) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.menuPagi = menuPagi;
        this.menuSiang = menuSiang;
        this.menuMalam = menuMalam;
    }

    private  String tanggal;
    private ArrayList<MakananModel> menuPagi;
    private ArrayList<MakananModel> menuSiang;

    public ArrayList<MakananModel> getMenuPagi() {
        return menuPagi;
    }

    public void setMenuPagi(ArrayList<MakananModel> menuPagi) {
        this.menuPagi = menuPagi;
    }

    public ArrayList<MakananModel> getMenuSiang() {
        return menuSiang;
    }

    public void setMenuSiang(ArrayList<MakananModel> menuSiang) {
        this.menuSiang = menuSiang;
    }

    public ArrayList<MakananModel> getMenuMalam() {
        return menuMalam;
    }

    public void setMenuMalam(ArrayList<MakananModel> menuMalam) {
        this.menuMalam = menuMalam;
    }

    private ArrayList<MakananModel> menuMalam;

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
