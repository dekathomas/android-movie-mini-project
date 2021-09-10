package com.example.androidmovieminiproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidmovieminiproject.fragments.HomeFragment;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.fragments.WatchLaterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Hide title bar
         */
        getSupportActionBar().hide();

        loadFragment(new HomeFragment());

        /*BottomNavigationView bottomNavigationView = findViewById(R.id.bottomMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomListener);*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.menuHomeButton:
                    fragment = new HomeFragment();
                    break;
                case R.id.menuWatchLaterButton:
                    fragment = new WatchLaterFragment();
                    break;
            }

            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commitNow();
        return true;
    }

    private void onNavigationItemSelected(MenuItem item) {

    }
}