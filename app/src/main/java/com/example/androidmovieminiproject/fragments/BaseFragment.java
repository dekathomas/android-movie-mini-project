package com.example.androidmovieminiproject.fragments;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.androidmovieminiproject.utility.LoadingDialog;

public class BaseFragment extends Fragment {
    private View view;
    private int animationDuration;
    private LoadingDialog loading;

    public void initAnimationVariables(View view) {
        loading = new LoadingDialog(getActivity());
        this.view = view;

        loading.show();
        view.setVisibility(View.GONE);
        animationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    public void showScrollView() {
        loading.hide();
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);

        view.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .setListener(null);
    }
}
