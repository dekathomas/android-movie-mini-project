package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.adapters.MovieAdapter;
import com.example.androidmovieminiproject.adapters.TvAdapter;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.AppProperties;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;
import com.example.androidmovieminiproject.viewmodel.SearchViewModel;

public class SearchActivity extends BaseActivity implements RecyclerViewClick {
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;
    private MovieAdapter movieAdapter;
    private SearchViewModel viewModel;

    private ImageView backButton;
    private String searchType;
    private SearchView searchViewInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initVariables();
        setPlaceholderOfInputText();
        setEventListener();

//        getFilmList();
    }

    private void initVariables() {
        Intent intent = getIntent();
        searchType = intent.getStringExtra(AppProperties.searchType);
        backButton = findViewById(R.id.searchBackPage);
        searchViewInput = findViewById(R.id.searchViewInput);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        recyclerView = findViewById(R.id.filmSearchRecylerView);
    }

    private void setPlaceholderOfInputText() {
        String placeholder = "";
        if (searchType.equalsIgnoreCase(AppProperties.tv)) {
            placeholder = getString(R.string.search_placeholder_tv);
            searchViewInput.setQueryHint(placeholder);
        } else {
            placeholder = getString(R.string.search_placeholder_movie);
            searchViewInput.setQueryHint(placeholder);
        }
    }

    private void getFilmList() {
        if (searchType.equalsIgnoreCase(AppProperties.tv)) {
            getTvListFromDB();
        } else if (searchType.equalsIgnoreCase(AppProperties.movie)) {
            getMovieListFromDB();
        }
    }

    private void getTvListFromDB() {
        viewModel.getTvListFromDB();
        initRecyclerView(AppProperties.tv);
    }

    private void getMovieListFromDB() {
        viewModel.getMovieListFromDB();
        initRecyclerView(AppProperties.movie);
    }

    private void initRecyclerView(String type) {
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this.getApplicationContext(), 3);

        if (type.equals(AppProperties.tv)) {
            getTvList(gridLayoutManager);
        } else if (type.equals(AppProperties.movie)) {
            getMovieList(gridLayoutManager);
        }
    }

    private void getTvList(GridLayoutManager gridLayoutManager) {
        viewModel.tvList.observe(this, tvDetails -> {
            if (tvDetails != null && tvDetails.size() > 0) {
                for (TvDetail tvDetail : tvDetails) {
                    viewModel.insertTvToDB(tvDetail);
                }

                tvAdapter = new TvAdapter(tvDetails, this);
                recyclerView.setAdapter(tvAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
                tvAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getMovieList(GridLayoutManager gridLayoutManager) {
        viewModel.movieList.observe(this, movieDetailList -> {
            if (movieDetailList != null && movieDetailList.size() > 0) {
                for (MovieDetail movieDetail : movieDetailList) {
                    viewModel.insertMovieToDB(movieDetail);
                }

                movieAdapter = new MovieAdapter(movieDetailList, this,"");
                recyclerView.setAdapter(movieAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
                movieAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        if (searchType.equals(AppProperties.tv)) {
            TvDetail tvDetail = viewModel.getDetailTvFromDB(position);

            if (tvDetail != null) {
                goToDetailPage(tvDetail.getId(), AppProperties.tv);
            }
        }
        else if (searchType.equals(AppProperties.movie)) {
            MovieDetail movieDetail = viewModel.getDetailMovieFromDB(position);

            if (movieDetail != null) {
                goToDetailPage(movieDetail.getId(), AppProperties.movie);
            }
        }
    }

    private void goToDetailPage(int tvId, String type) {
        Intent intent = new Intent(this.getApplicationContext(), DetailMovieActivity.class);
        intent.putExtra(AppProperties.detailItemId, tvId);
        intent.putExtra(AppProperties.detailItemType, type);
        startActivity(intent);
    }

    private void setEventListener() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        searchViewInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                if (searchType.equals(AppProperties.tv)) {
//                    tvAdapter.getFilter().filter(name);
                } else if (searchType.equals(AppProperties.movie)) {
//                    movieAdapter.getFilter().filter(name);
                    viewModel.searchMovieByName(name);
                    initRecyclerView(AppProperties.movie);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}