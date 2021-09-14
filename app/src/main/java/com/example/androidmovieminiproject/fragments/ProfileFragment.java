package com.example.androidmovieminiproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.activities.LoginActivity;
import com.example.androidmovieminiproject.viewmodel.UserLoginViewModel;

public class ProfileFragment extends Fragment {
    private TextView profileName;
    private Button logoutButton;
    private UserLoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);
        profileName = getActivity().findViewById(R.id.profileName);
        logoutButton = getActivity().findViewById(R.id.logoutBotton);

        setData();
        initButton();
    }

    private void setData() {
        String username = viewModel.getUsername();
        profileName.setText(username);
    }

    private void initButton() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        viewModel.logout();
        goToLoginPage();
    }

    private void goToLoginPage() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}