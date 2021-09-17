package com.example.androidmovieminiproject.security;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

public class SessionManager {
    public static final String SESSION_PREFERENCE = "SESSION_PREFERENCE";
    public static final String SESSION_EXPIRY_TIME = "SESSION_EXPIRY_TIME";
    public static final String SESSION_USER_NAME = "SESSION_USER_NAME";
    private static final String APPLICATION_LANGUAGE = "APLICATION_LANGUAGE";
    private static SessionManager INSTANCE;

    public static SessionManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SessionManager();
        }
        return INSTANCE;
    }

    public void startUserSession(Context context, String name){
        Calendar calendar = Calendar.getInstance();
        Date userLoggedTime = calendar.getTime();
        calendar.setTime(userLoggedTime);
        calendar.add(Calendar.HOUR, 24);
        Date expiryTime = calendar.getTime();

        SharedPreferences.Editor editor = context
            .getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
            .edit();

        editor.putLong(SESSION_EXPIRY_TIME, expiryTime.getTime());
        editor.putString(SESSION_USER_NAME, name);
        editor.apply();
    }

    public Boolean isSessionActive(Context context) {
        Date sessionExpiresAt = new Date(getExpiryDateFromPreference(context));
        return !new Date().after(sessionExpiresAt);
    }

    private long getExpiryDateFromPreference(Context context){
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getLong(SESSION_EXPIRY_TIME, 0);
    }

    public String getUserName(Context context){
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getString(SESSION_USER_NAME, "");
    }

    public void endUserSession(Context context){
        SharedPreferences.Editor editor =
                context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    public String getLanguage(Context context) {
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getString(APPLICATION_LANGUAGE, "");
    }

    public void saveLanguage(Context context, String language) {
        SharedPreferences.Editor editor = context
            .getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
            .edit();

        editor.putString(APPLICATION_LANGUAGE, language);
        editor.apply();
    }

}

