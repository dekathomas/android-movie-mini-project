package com.example.androidmovieminiproject.repository;

import android.app.Application;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.UserDetailDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.User.UserDetail;
import com.example.androidmovieminiproject.model.User.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginRepository {
    private final RetrofitService retrofit;
    private final UserDetailDao userDetailDao;

    public UserLoginRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDetailDao = database.userDetailDao();
        retrofit = new RetrofitService();
    }

    public void login(String email, String password, requestCallback callback) {
        retrofit.getOneApi()
            .login(email, password)
            .enqueue(new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                    if (response.body() == null) {
                        callback.onSuccess(null);
                        return;
                    }

                    if (response.body().getStatus()) {
                        callback.onSuccess(response.body().getUserDetail());
                    }
                    else {
                        callback.onSuccess(null);
                    }
                }

                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {
                    callback.onFailed(t.getMessage());
                }
            });

    }

    public void deleteAllTable() {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.deleteALlTables();
            }
        });
    }

    public interface requestCallback {
        void onSuccess(UserDetail userDetail);
        void onFailed(String message);
    }
}
