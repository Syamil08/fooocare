package com.example.fooocare;

public class Screenitem {
    String Title, Description;
    int ScreenImg;

    public Screenitem(String title, String description, int screenImg) {
        this.Title = title;
        this.Description = description;
        this.ScreenImg = screenImg;
    }

    public void setTitle(String title){
        this.Title = title;
    }


    public void setDescription(String description){
        this.Description = description;
    }


    public void setScreenImg(int screenImg){
        this.ScreenImg = screenImg;
    }


    public String getTitle(){
        return Title;
    }


    public String getDescription(){
        return Description;
    }


    public int getScreenImg(){
        return ScreenImg;
    }
}