package com.example.mymondial;

import android.app.Application;

public class Global_variable extends Application {

    private String years;

    public String getyears() {
        return years;
    }

    public void setyears(String years) {
        this.years = years;
    }
}