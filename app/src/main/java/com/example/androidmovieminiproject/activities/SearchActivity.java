package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidmovieminiproject.R;

public class SearchActivity extends BaseActivity {
    private EditText inputSearchText;
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
        setEventListener();
    }

    private void initVariables() {
        Intent intent = getIntent();
        searchType = intent.getStringExtra(String.valueOf(R.string.search_type));
        inputSearchText = findViewById(R.id.searchInput);
        clearSearch = findViewById(R.id.searchClearText);
        backButton = findViewById(R.id.searchBackPage);
    }

    private void setPlaceholderOfInputText() {
        if (searchType.equalsIgnoreCase("tv")) {
            inputSearchText.setHint(R.string.search_placeholder_tv);
        } else {
            inputSearchText.setHint(R.string.search_placeholder_movie);
        }
    }

    private void setEventListener() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        inputSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}