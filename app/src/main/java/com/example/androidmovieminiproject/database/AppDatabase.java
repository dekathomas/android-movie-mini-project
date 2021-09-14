package com.example.androidmovieminiproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidmovieminiproject.dao.MovieDetailDao;
import com.example.androidmovieminiproject.dao.UserDetailDao;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.User.UserDetail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        MovieDetail.class, UserDetail.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase instance;
    private static final int THREAD_INSTANCE = 4;

    public abstract MovieDetailDao movieDetailDao();
    public abstract UserDetailDao userDetailDao();

    public static final ExecutorService executorService =
            Executors.newFixedThreadPool(THREAD_INSTANCE);

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "android_movie_app")
                    .fallbackToDestructiveMigration()
                    .build();
            }
        }
        return instance;
    }

}
