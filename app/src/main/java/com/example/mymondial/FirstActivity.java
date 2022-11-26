package com.example.mymondial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    ImageButton russia;
    ImageButton qatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        russia = (ImageButton) findViewById(R.id.russia);
        russia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Global_variable) getApplication()).setyears("2018");
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
                finish();
            }
        });

        qatar = (ImageButton) findViewById(R.id.quatar);
        qatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Global_variable) getApplication()).setyears("2022");
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}