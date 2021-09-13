package com.example.androidmovieminiproject.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidmovieminiproject.security.SessionManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        Boolean isLoggedIn = SessionManager.getInstance().isSessionActive(this);

        if (!isLoggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onResume();
    }
}
