package com.example.androidmovieminiproject.utility;

import android.content.Context;
import android.widget.Toast;

public class AlertDialog {

    public static void error(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void success(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
