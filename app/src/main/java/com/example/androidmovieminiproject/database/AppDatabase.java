package com.example.androidmovieminiproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidmovieminiproject.dao.HomeDao;
import com.example.androidmovieminiproject.dao.MovieDao;
import com.example.androidmovieminiproject.dao.TvDao;
import com.example.androidmovieminiproject.dao.UserDetailDao;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.model.User.UserDetail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        MovieDetail.class, UserDetail.class, HomeDetail.class, TvDetail.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase instance;
    private static final int THREAD_INSTANCE = 4;

    public abstract MovieDao movieDetailDao();
    public abstract UserDetailDao userDetailDao();
    public abstract TvDao tvDao();
    public abstract HomeDao homeDao();

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
