package com.example.androidmovieminiproject.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.activities.DetailMovieActivity;
import com.example.androidmovieminiproject.activities.SearchActivity;
import com.example.androidmovieminiproject.adapters.MovieAdapter;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.utility.AppProperties;
import com.example.androidmovieminiproject.viewmodel.MovieViewModel;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class MovieFragment extends BaseFragment implements RecyclerViewClick {
    private RecyclerView popularRecyclerView;
    private RecyclerView upcomingRecyclerView;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private ConstraintLayout componentSearch;

    private Boolean isLoadingUpcoming;
    private Boolean isLoadingPopular;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables();
        initViewModel();
        initPopularRecylcerView();
        initUpcomingRecylcerView();

        setSearchListener();
    }

    private void initVariables() {
        isLoadingUpcoming = true;
        isLoadingPopular = true;

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        popularRecyclerView = getActivity().findViewById(R.id.popularMovieRecyclerView);
        upcomingRecyclerView = getActivity().findViewById(R.id.upcomingMovieRecyclerView);
        componentSearch = getActivity().findViewById(R.id.componentMovieSearchBox);

        initAnimationVariables(getActivity().findViewById(R.id.movieScrollView));
    }

    private void initViewModel() {
        movieViewModel.getPopularMovieList();
        movieViewModel.getUpcomingMovieList();
    }

    private void initPopularRecylcerView() {
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        movieViewModel.mutablePopularMovieList.observe(getActivity(), movieDetails -> {
            if (movieDetails != null && movieDetails.size() > 0) {
                initRecyclerView(movieDetails, popularRecyclerView, gridLayoutManager, AppProperties.popularMovie);
                isLoadingPopular = false;
            }

            if (!isLoadingPopular && !isLoadingUpcoming) {
                System.out.println("---------------------------------");
                System.out.println("dari popular");
                showScrollView();
            }
            movieViewModel.mutablePopularMovieList.removeObservers(getActivity());
        });
    }

    private void initUpcomingRecylcerView() {
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        movieViewModel.mutableUpcomingMovieList.observe(getActivity(), movieDetails -> {
            if (movieDetails != null && movieDetails.size() > 0) {
                initRecyclerView(movieDetails, upcomingRecyclerView, gridLayoutManager, AppProperties.upcomingMovie);
                isLoadingUpcoming = false;
            }

            if (!isLoadingPopular && !isLoadingUpcoming) {
                System.out.println("---------------------------------");
                System.out.println("dari upcoming");
                showScrollView();
            }
            movieViewModel.mutableUpcomingMovieList.removeObservers(getActivity());
        });
    }

    private void initRecyclerView(List<MovieDetail> movieDetailList, RecyclerView recyclerView,
          GridLayoutManager gridLayoutManager, String adapterType
    ) {
        insertMovieDetailToDatabase(movieDetailList);
        movieAdapter = new MovieAdapter(movieDetailList, this, adapterType);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter.notifyDataSetChanged();
    }

    private void insertMovieDetailToDatabase(List<MovieDetail> movieList) {
        if (movieList.size() > 0) {
            for (MovieDetail movieDetail : movieList) {
                movieViewModel.insertMovieDetail(movieDetail);
            }
        }
    }

    @Override
    public void onItemClick(int position, String type){
        MovieDetail movieDetail = movieViewModel.getMovieDetail(position, type);

        if (movieDetail != null) {
            goToDetailPage(movieDetail.getId());
        }
    }

    private void goToDetailPage(int homeId) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(AppProperties.detailItemId, homeId);
        intent.putExtra(AppProperties.detailItemType, AppProperties.movie);
        startActivity(intent);
    }

    private void setSearchListener() {
        componentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra(AppProperties.searchType, AppProperties.movie);
                startActivity(intent);
            }
        });
    }
}