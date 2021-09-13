package com.example.androidmovieminiproject.model.User;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Entity(tableName = "user")
public class UserLogin {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Boolean status;
    
    @SerializedName("data")
    @ColumnInfo(name = "user_detail")
    private UserDetail userDetail;

}