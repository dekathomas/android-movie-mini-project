package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidmovieminiproject.R;

public class SearchActivity extends BaseActivity {
    private EditText editText;
    private ImageView clearSearch;
    private ImageView backButton;
    private String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initVariables();
        setPlaceholderOfInputText();
        setButtonListener();
    }

    private void initVariables() {
        Intent intent = getIntent();
        searchType = intent.getStringExtra(String.valueOf(R.string.search_type));
        editText = findViewById(R.id.searchInput);
        clearSearch = findViewById(R.id.searchClearText);
        backButton = findViewById(R.id.searchBackPage);
    }

    private void setPlaceholderOfInputText() {
        if (searchType.equalsIgnoreCase("tv")) {
            editText.setHint(R.string.search_placeholder_tv);
        } else {
            editText.setHint(R.string.search_placeholder_movie);
        }
    }

    private void setButtonListener() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}