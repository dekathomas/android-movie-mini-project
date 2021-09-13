package com.example.androidmovieminiproject.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;

import com.example.androidmovieminiproject.R;

public class LoadingDialog {
    private Activity activity;
    private ProgressDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void show() {
        dialog = new ProgressDialog(activity);
        dialog.show();
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void hide() {
        dialog.dismiss();
    }

}
