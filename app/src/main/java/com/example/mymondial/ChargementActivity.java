package com.example.mymondial;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


// Permet de lancer l'animation de chargement durant 2100 ms avant de lancer FirstActivity
public class ChargementActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chargement);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ChargementActivity.this, FirstActivity.class));
                finish();
            }
        },2100);

    }
}

