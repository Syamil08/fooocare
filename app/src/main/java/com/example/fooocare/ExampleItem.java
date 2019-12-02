package com.example.fooocare;

public class ExampleItem {
    private  String mText1;
    private  String mText2;
    private  String mMakan;
    private  int mKalori;
    private  boolean expanded;

    public ExampleItem(String mText1, String mText2, String mMakan, int mKalori) {
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.mMakan = mMakan;
        this.mKalori = mKalori;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getmMakan() {
        return mMakan;
    }

    public void setmMakan(String mMakan) {
        this.mMakan = mMakan;
    }

    public int getmKalori() {
        return mKalori;
    }

    public void setmKalori(int mKalori) {
        this.mKalori = mKalori;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
}
