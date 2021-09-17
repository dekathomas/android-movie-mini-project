package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.User.UserDetail;
import com.example.androidmovieminiproject.repository.UserLoginRepository;
import com.example.androidmovieminiproject.security.SessionManager;
import com.example.androidmovieminiproject.utility.AlertDialog;

public class UserLoginViewModel extends AndroidViewModel {
    private final UserLoginRepository repository;
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
                    userDetail.postValue(user);
                } else {
                    userDetail.postValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), String.valueOf(R.string.error_general));
                userDetail.postValue(null);
            }
        });
    }

    public void logout() {
        SessionManager.getInstance().endUserSession(getApplication());
        repository.deleteAllTable();
    }

    public String getUsername() {
        return SessionManager.getInstance() .getUserName(getApplication());
    }
}
