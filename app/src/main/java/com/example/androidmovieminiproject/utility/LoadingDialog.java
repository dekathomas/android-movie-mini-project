package com.example.androidmovieminiproject.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;

import com.example.androidmovieminiproject.R;

public class LoadingDialog {
    private final Activity activity;
    private final ProgressDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
        this.dialog = new ProgressDialog(activity);
    }

    public void show() {
        dialog.show();
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void hide() {
        dialog.dismiss();
    }

}
