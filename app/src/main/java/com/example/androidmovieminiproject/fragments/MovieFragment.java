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
import com.example.androidmovieminiproject.adapters.MovieAdapter;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.viewmodel.MovieViewModel;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class MovieFragment extends Fragment implements RecyclerViewClick {


    private RecyclerView popularRecyclerView;
    private RecyclerView upcomingRecyclerView;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private LoadingDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new LoadingDialog(getActivity());
        loading.show();

        initViewModel();
        initPopularRecylcerView();
        initUpcomingRecylcerView();
    }

    private void initViewModel() {
        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.getPopularMovieList();
        movieViewModel.getUpcomingMovieList();
    }

    private void initPopularRecylcerView() {
        popularRecyclerView = getActivity().findViewById(R.id.popularMovieRecyclerView);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        movieViewModel.mutablePopularMovieList.observe(getActivity(), movieDetails -> {
            insertMovieDetailToDatabase(movieDetails);
            System.out.println("========================= INI POPULAR MOVIES COI ==========================");
            movieAdapter = new MovieAdapter(movieDetails, this, "popular");
            popularRecyclerView.setAdapter(movieAdapter);
            popularRecyclerView.setLayoutManager(gridLayoutManager);
            movieAdapter.notifyDataSetChanged();
            loading.hide();
        });
    }

    private void initUpcomingRecylcerView() {
        // TODO film di upcoming beda coi di list sama detail
        upcomingRecyclerView = getActivity().findViewById(R.id.upcomingMovieRecyclerView);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        movieViewModel.mutableUpcomingMovieList.observe(getActivity(), movieDetails -> {
            insertMovieDetailToDatabase(movieDetails);
            System.out.println("========================= INI UPCOMING MOVIES COI ==========================");
            movieAdapter = new MovieAdapter(movieDetails, this, "upcoming");
            upcomingRecyclerView.setAdapter(movieAdapter);
            upcomingRecyclerView.setLayoutManager(gridLayoutManager);
            movieAdapter.notifyDataSetChanged();
            loading.hide();
        });
    }

    private void insertMovieDetailToDatabase(List<MovieDetail> movieList) {
        if (movieList.size() > 0) {
            for (MovieDetail movieDetail : movieList) {
                movieViewModel.insertHomeDetail(movieDetail);
            }
        }
    }

    @Override
    public void onItemClick(int position, String type){
        MovieDetail movieDetail = movieViewModel.getMovieDetail(position, type);
        goToDetailPage(movieDetail.getId());
    }

    private void goToDetailPage(int homeId) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(String.valueOf(R.string.detail_item_id), homeId);
        intent.putExtra(String.valueOf(R.string.detail_item_type), "movie");
        startActivity(intent);
    }
}