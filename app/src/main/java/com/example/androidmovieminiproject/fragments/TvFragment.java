package com.example.androidmovieminiproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.activities.DetailMovieActivity;
import com.example.androidmovieminiproject.activities.SearchActivity;
import com.example.androidmovieminiproject.adapters.TvAdapter;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.AlertDialog;
import com.example.androidmovieminiproject.utility.AppProperties;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;
import com.example.androidmovieminiproject.viewmodel.TvViewModel;

import java.util.List;

public class TvFragment extends BaseFragment implements RecyclerViewClick {
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;
    private TvViewModel tvViewModel;
    private ConstraintLayout componentSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables();
        initViewModel();
        initPopularRecylcerView();
        setSearchListener();
        showScrollView();
    }

    private void initVariables() {
        componentSearch = getActivity().findViewById(R.id.componentSearchBox);
        tvViewModel = new ViewModelProvider(getActivity()).get(TvViewModel.class);
        recyclerView = getActivity().findViewById(R.id.tvPopularRecyclerView);

        initAnimationVariables(getActivity().findViewById(R.id.tvScrollView));
    }

    private void initViewModel() {
        tvViewModel.getAllTvPopular();
    }

    private void initPopularRecylcerView() {
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        tvViewModel.tvList.observe(getActivity(), tvDetails -> {
            if (tvDetails != null && tvDetails.size() > 0) {
                insertTvDetailToDatabase(tvDetails);

                tvAdapter = new TvAdapter(tvDetails, this);
                recyclerView.setAdapter(tvAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
                tvAdapter.notifyDataSetChanged();
            }
        });
    }

    private void insertTvDetailToDatabase(List<TvDetail> tvList) {
        for (TvDetail tvDetail : tvList) {
            tvViewModel.insertTvDetail(tvDetail);
        }
    }

    @Override
    public void onItemClick(int position, String type) {
        TvDetail tvDetail = tvViewModel.getTv(position);

        if (tvDetail != null) {
            goToDetailPage(tvDetail.getId());
        }
    }

    private void goToDetailPage(int tvId) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(AppProperties.detailItemId, tvId);
        intent.putExtra(AppProperties.detailItemType, AppProperties.tv);
        startActivity(intent);
    }

    private void setSearchListener() {
        componentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra(AppProperties.searchType, "tv");
                startActivity(intent);
            }
        });
    }
}