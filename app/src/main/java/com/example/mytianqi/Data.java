package com.example.mytianqi;

import android.media.Image;

public class Data {
    int image;
    private String time;
    private String Temp;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public Data( String time,int image, String temp) {
        this.image = image;
        this.time = time;
        Temp = temp;
    }
}
