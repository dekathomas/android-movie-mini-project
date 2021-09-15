package com.example.androidmovieminiproject.fragments;

import android.content.Intent;
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
import com.example.androidmovieminiproject.activities.DetailMovieActivity;
import com.example.androidmovieminiproject.adapters.TvAdapter;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.AlertDialog;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;
import com.example.androidmovieminiproject.viewmodel.TvViewModel;

import java.util.List;

public class TvFragment extends Fragment implements RecyclerViewClick {
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
            insertTvDetailToDatabase(tvDetails);

            tvAdapter = new TvAdapter(tvDetails, this);
            recyclerView.setAdapter(tvAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);
            tvAdapter.notifyDataSetChanged();
            loading.hide();
        });
    }

    private void insertTvDetailToDatabase(List<TvDetail> tvList) {
        for (TvDetail tvDetail : tvList) {
            tvViewModel.insertTvDetail(tvDetail);
        }
    }

    @Override
    public void onItemClick(int position) {
        TvDetail tvDetail = tvViewModel.getTv(position);
        goToDetailPage(tvDetail.getId());
    }

    private void goToDetailPage(int tvId) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(String.valueOf(R.string.detail_item_id), tvId);
        intent.putExtra(String.valueOf(R.string.detail_item_type), "tv");
        startActivity(intent);
    }
}