package com.example.androidmovieminiproject.utility;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHelper {

    public static void changeLanguage(Application application, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = application.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}
