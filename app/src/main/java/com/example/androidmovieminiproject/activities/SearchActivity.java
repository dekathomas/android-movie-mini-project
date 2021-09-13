package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidmovieminiproject.R;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
    }
}