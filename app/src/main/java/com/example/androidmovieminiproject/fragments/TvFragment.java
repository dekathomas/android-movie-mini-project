package com.example.androidmovieminiproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.adapters.TvAdapter;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.viewmodel.TvViewModel;

public class TvFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;
    private TvViewModel tvViewModel;
    private LoadingDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new LoadingDialog(getActivity());
        loading.show();

        initViewModel();
        initPopularRecylcerView();
    }

    private void initViewModel() {
        tvViewModel = new ViewModelProvider(getActivity()).get(TvViewModel.class);
        tvViewModel.getAllTvPopular();
    }

    private void initPopularRecylcerView() {
        recyclerView = getActivity().findViewById(R.id.tvPopularRecyclerView);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        tvViewModel.tvList.observe(getActivity(), tvDetails -> {
            tvAdapter = new TvAdapter(tvViewModel.tvList.getValue());
            recyclerView.setAdapter(tvAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);
            tvAdapter.notifyDataSetChanged();
            loading.hide();
        });
    }
}