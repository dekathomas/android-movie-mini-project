package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.androidmovieminiproject.model.User.UserDetail;
import com.example.androidmovieminiproject.repository.UserLoginRepository;
import com.example.androidmovieminiproject.security.SessionManager;
import com.example.androidmovieminiproject.utility.AlertDialog;

public class UserLoginViewModel extends AndroidViewModel {
    private UserLoginRepository repository;

    public UserLoginViewModel(@NonNull Application application) {
        super(application);
        repository = new UserLoginRepository(application);
    }

    public void login(String email, String password) {
        repository.login(email, password, new UserLoginRepository.requestCallback() {
            @Override
            public void onSuccess(UserDetail userDetail) {
                if (userDetail != null) {
                    SessionManager.getInstance()
                        .startUserSession(getApplication(), userDetail.getFullName());
                    AlertDialog.success(getApplication(), "Success");
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), "Terjadi kesalahan dalam sistem");
            }
        });
    }
}
