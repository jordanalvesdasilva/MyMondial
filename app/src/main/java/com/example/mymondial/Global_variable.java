package com.example.mymondial;

import android.app.Application;

// Permet de définir la variable global years avec deux méthodes set et get
public class Global_variable extends Application {

    private String years;

    public String getyears() {
        return years;
    }

    public void setyears(String years) {
        this.years = years;
    }
}