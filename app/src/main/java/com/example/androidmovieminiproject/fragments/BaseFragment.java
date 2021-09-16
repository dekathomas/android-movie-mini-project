package com.example.androidmovieminiproject.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidmovieminiproject.utility.LoadingDialog;

public class BaseFragment extends Fragment {
    private View scrollView;
    private int animationDuration;
    private LoadingDialog loading;

    public void initAnimationVariables(View scrollView) {
        loading = new LoadingDialog(getActivity());
        this.scrollView = scrollView;

        loading.show();
        scrollView.setVisibility(View.GONE);
        animationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    public void showScrollView() {
        loading.hide();
        scrollView.setAlpha(0f);
        scrollView.setVisibility(View.VISIBLE);

        scrollView.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .setListener(null);
    }
}
