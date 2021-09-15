package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.User.UserDetail;


@Dao
public interface UserDetailDao {

    @Insert
    void insert(UserDetail userDetail);

}
