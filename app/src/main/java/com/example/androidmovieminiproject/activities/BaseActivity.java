package com.example.androidmovieminiproject.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.se.omapi.Session;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidmovieminiproject.security.SessionManager;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String language = SessionManager.getInstance().getLanguage(this);
        if (language != null && language != "") {
            changeLanguage(language);
        }
    }

    @Override
    protected void onResume() {
        Boolean isLoggedIn = SessionManager.getInstance().isSessionActive(this);
        String currentActivity = getClass().getName();

        if (!isLoggedIn && !currentActivity.equals(LoginActivity.class)) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (isLoggedIn && currentActivity.equals(LoginActivity.class)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        super.onResume();
    }

    private void changeLanguage(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
