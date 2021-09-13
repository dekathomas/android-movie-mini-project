package com.example.androidmovieminiproject.model.User;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Entity(tableName = "user_detail")
public class UserDetail {
    @PrimaryKey()
    @NonNull
    private String id;

    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    private String fullName;
}
