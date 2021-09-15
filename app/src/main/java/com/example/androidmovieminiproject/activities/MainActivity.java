package com.example.androidmovieminiproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidmovieminiproject.fragments.FavouriteFragment;
import com.example.androidmovieminiproject.fragments.HomeFragment;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.fragments.MovieFragment;
import com.example.androidmovieminiproject.fragments.ProfileFragment;
import com.example.androidmovieminiproject.fragments.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Hide title bar
         */
        getSupportActionBar().hide();

        loadFragment(new HomeFragment());

        BottomNavigationView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setOnNavigationItemSelectedListener(navListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.firstMenuButton:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.secondMenuButton:
                            selectedFragment = new TvFragment();
                            break;
                        case R.id.thirdMenuButton:
                            selectedFragment = new FavouriteFragment();
                            break;
                        case R.id.fourthMenuButton:
                            selectedFragment = new ProfileFragment();
                            break;
                        default:
                            selectedFragment = new HomeFragment();
                            break;
                    }
                    loadFragment(selectedFragment);

                    return true;
                }
            };

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commitNow();
        return true;
    }
}