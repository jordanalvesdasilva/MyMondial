package com.example.mymondial;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymondial.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    final private int DIRECTION_RIGHT = 1;
    final private int DIRECTION_LEFT = 2;
    final private int DIRECTION_UP = 3;
    final private int DIRECTION_DOWN = 4;


    public Map<Integer, String> directionsMap;
    //gesture detector
    private GestureDetectorCompat gDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        directionsMap = new HashMap<>();
        directionsMap.put(DIRECTION_RIGHT, "right");
        directionsMap.put(DIRECTION_LEFT, "left");
        directionsMap.put(DIRECTION_UP, "up");
        directionsMap.put(DIRECTION_DOWN, "down");
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home_Fragment(),"Home_Fragment" );

        binding.navbar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.homes:
                    replaceFragment(new Home_Fragment(), "Home_Fragment");
                    break;
                case R.id.groupes:
                    replaceFragment(new Groupes_Fragment(), "Groupes_Fragment");
                    break;
                case R.id.joueur:
                    replaceFragment(new Joueurs_Fragment(), "Joueurs_Fragment");
                    break;
                case R.id.phasefinale:
                    replaceFragment(new PhaseFinale_Fragment(), "PhaseFinale_Fragment");
                    break;
            }
            return true;
        });
        gDetect = new GestureDetectorCompat(this, new GestureListener());
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        gDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void replaceFragment(Fragment fragment, String TAG){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Layout, fragment, TAG);
        fragmentTransaction.commit();
    }



    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private float flingMin = 100;
        private float velocityMin = 100;
        FragmentManager fragmentManager = getSupportFragmentManager();
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            //calculate the change in X position within the fling gesture
            float horizontalDiff = e2.getX() - e1.getX();
            //calculate the change in Y position within the fling gesture
            float verticalDiff = e2.getY() - e1.getY();

            float absHDiff = Math.abs(horizontalDiff);
            float absVDiff = Math.abs(verticalDiff);
            float absVelocityX = Math.abs(velocityX);

            if (absHDiff > absVDiff && absHDiff > flingMin && absVelocityX > velocityMin && horizontalDiff > 0) {
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.Layout);
                if (currentFragment.getTag().equals("Match_Fragment")){
                    replaceFragment(new Home_Fragment(), "Home_Fragment");
                }
                if (currentFragment.getTag().equals("Home_Fragment") || currentFragment.getTag().equals("Groupes_Fragment") || currentFragment.getTag().equals("Joueurs_Fragment") || currentFragment.getTag().equals("PhaseFinale_Fragment")){
                    startActivity(new Intent(MainActivity.this, FirstActivity.class));
                    finish();
                }
            }
            return true;
        }
    }
}