package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.viewmodel.UserLoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private UserLoginViewModel viewModel;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loading = new LoadingDialog(this);
        viewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                login();
            }
        });
    }

    private void login() {
        validate();
    }

    private void validate() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.error_empty_field, Toast.LENGTH_SHORT).show();
        }

        viewModel.login(email, password);
        viewModel.userDetail.observe(this, user -> {
            if (user != null) {
                goToMainPage();
            } else {
                loading.hide();
                Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}