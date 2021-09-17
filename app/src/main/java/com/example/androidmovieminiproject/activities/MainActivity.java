package com.example.androidmovieminiproject.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidmovieminiproject.fragments.FavouriteFragment;
import com.example.androidmovieminiproject.fragments.MovieFragment;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.fragments.ProfileFragment;
import com.example.androidmovieminiproject.fragments.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new MovieFragment());

        BottomNavigationView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setOnNavigationItemSelectedListener(navListener);

        bottomMenu.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.firstMenuButton:
                            selectedFragment = new MovieFragment();
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