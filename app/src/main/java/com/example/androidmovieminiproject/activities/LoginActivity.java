package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.viewmodel.UserLoginViewModel;

public class LoginActivity extends BaseActivity {
    private UserLoginViewModel viewModel;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        validate();
    }

    private Boolean validate() {
        String email = emailInput.getText().toString();
        String password = emailInput.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            return false;
        }

        viewModel.login(email, password);
        return true;
    }
}