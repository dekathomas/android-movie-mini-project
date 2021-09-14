package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.model.User.UserDetail;
import com.example.androidmovieminiproject.model.User.UserLogin;
import com.example.androidmovieminiproject.repository.UserLoginRepository;
import com.example.androidmovieminiproject.security.SessionManager;
import com.example.androidmovieminiproject.utility.AlertDialog;

public class UserLoginViewModel extends AndroidViewModel {
    private UserLoginRepository repository;
    public MutableLiveData<UserDetail> userDetail = new MutableLiveData<>();

    public UserLoginViewModel(@NonNull Application application) {
        super(application);
        repository = new UserLoginRepository(application);
    }

    public void login(String email, String password) {
        repository.login(email, password, new UserLoginRepository.requestCallback() {
            @Override
            public void onSuccess(UserDetail user) {
                if (user != null) {
                    SessionManager.getInstance()
                        .startUserSession(getApplication(), user.getFullName());
                    userDetail.setValue(user);
                } else {
                    userDetail.setValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), "Terjadi kesalahan dalam sistem");
                userDetail.setValue(null);
            }
        });
    }
}
