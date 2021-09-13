package com.example.androidmovieminiproject.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidmovieminiproject.dao.MovieDetailDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Database extends RoomDatabase {

    public static Database instance;

    public abstract MovieDetailDao movieDetailDao();

    private final int THREAD_INSTANCE = 4;

    public static final ExecutorService executorService =
            Executors.newFixedThreadPool(instance.THREAD_INSTANCE);

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "android_movie_app")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
