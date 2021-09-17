package com.example.androidmovieminiproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidmovieminiproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InformationActivity extends BaseActivity {
    private Button davidLinkedin;
    private Button dkLinkedin;
    private FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initVraiables();
        setButtonEvent();
    }

    private void initVraiables() {
        davidLinkedin = findViewById(R.id.davidLinkedinButton);
        dkLinkedin = findViewById(R.id.dkLinkedinButton);
        backButton = findViewById(R.id.componentButtonBack);
    }

    private void setButtonEvent() {
        dkLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { goToLinkedinWebsite("https://bit.ly/ln-dk");
            }
        });

        davidLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { goToLinkedinWebsite("https://www.linkedin.com/in/david-triady-b07a98137");
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void goToLinkedinWebsite(String url) {
        Uri linkedinWebpage = Uri.parse(url);
        Intent linkedIntent = new Intent(Intent.ACTION_VIEW, linkedinWebpage);
        startActivity(linkedIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}