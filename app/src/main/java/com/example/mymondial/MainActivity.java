package com.example.mymondial;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymondial.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home_Fragment());

        binding.navbar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.homes:
                    replaceFragment(new Home_Fragment());
                    break;
                case R.id.groupes:
                    replaceFragment(new Groupes_Fragment());
                    break;
                case R.id.joueur:
                    replaceFragment(new Joueurs_Fragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Layout, fragment);
        fragmentTransaction.commit();
    }
}