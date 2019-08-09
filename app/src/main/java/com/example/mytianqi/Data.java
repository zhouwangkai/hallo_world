package com.example.mytianqi;

public class Data {
    private String time;
    private String Temp;

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

    public Data(String time, String temp) {
        this.time = time;
        Temp = temp;
    }
}
