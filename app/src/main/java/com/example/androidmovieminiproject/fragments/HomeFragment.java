package com.example.androidmovieminiproject.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.activities.DetailMovieActivity;
import com.example.androidmovieminiproject.adapters.HomeAdapter;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.viewmodel.HomeViewModel;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewClick {


    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private HomeViewModel homeViewModel;
    private LoadingDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.getMovieList();
    }

    private void initPopularRecylcerView() {
        recyclerView = getActivity().findViewById(R.id.homeRecyclerView);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        homeViewModel.movieList.observe(getActivity(), homeDetails -> {
            insertHomeDetailToDatabase(homeDetails);
            homeAdapter = new HomeAdapter(homeDetails, this);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);
            homeAdapter.notifyDataSetChanged();
            loading.hide();
        });
    }

    private void insertHomeDetailToDatabase(List<HomeDetail> homeList) {
        for (HomeDetail homeDetail : homeList) {
            homeViewModel.insertHomeDetail(homeDetail);
        }
    }


    @Override
    public void onItemClick(int position){
        HomeDetail homeDetail = homeViewModel.getHome(position);
        goToDetailPage(homeDetail.getId());
    }

    private void goToDetailPage(int homeId) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(String.valueOf(R.string.detail_item_id), homeId);
        intent.putExtra(String.valueOf(R.string.detail_item_type), "home");
        startActivity(intent);
    }
}