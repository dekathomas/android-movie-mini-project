package com.example.androidmovieminiproject.activities;

import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.security.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class SettingActivity extends BaseActivity {
    private RadioGroup rgLanguage;
    private RadioButton rbEnglish, rbIndonesian;
    private FloatingActionButton backButton;
    private String language = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initialize();
        setDefaultLang();
        initButtonEvent();
    }

    private void initialize() {
        rgLanguage = findViewById(R.id.radioGroupChanegLanguage);
        rbEnglish = findViewById(R.id.radioButtonEnglish);
        rbIndonesian = findViewById(R.id.radioButtonIndonesian);
        backButton = findViewById(R.id.componentButtonBack);
    }

    private void setDefaultLang() {
        Locale mCurrent = getResources().getConfiguration().locale;

        if (mCurrent.toString().equals("en") || mCurrent.toString().equals("en_US")) {
            rbEnglish.setChecked(true);
            setUnselectedButton(rbIndonesian);
            setSelectedButton(rbEnglish);
        } else if (mCurrent.toString().equals("in") || mCurrent.toString().equals("in_ID")) {
            rbIndonesian.setChecked(true);
            setUnselectedButton(rbEnglish);
            setSelectedButton(rbIndonesian);
        }
    }

    private void initButtonEvent() {
        rgLanguage.setOnCheckedChangeListener((group, checkedId) -> {
            if (rbEnglish.isChecked()) {
                setLocale(this, "en");
                setUnselectedButton(rbIndonesian);
                setSelectedButton(rbEnglish);
            } else if (rbIndonesian.isChecked()) {
                setLocale(this, "in");
                setUnselectedButton(rbEnglish);
                setSelectedButton(rbIndonesian);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
    }

    private void setUnselectedButton(Button button) {
        button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.radio_button_unselected_state));
        button.setTextColor(Color.parseColor("#ffffff"));
    }

    private void setSelectedButton(Button button) {
        button.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.radio_button_selected_state));
        button.setTextColor(Color.parseColor("#ffffff"));
    }

    public void setLocale(Activity activity, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        SessionManager.getInstance().saveLanguage(getApplication(), lang);

        Intent intent = new Intent(this, SettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}