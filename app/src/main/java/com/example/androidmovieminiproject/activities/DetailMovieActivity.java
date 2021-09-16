package com.example.androidmovieminiproject.activities;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.Favourite;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.AppProperties;
import com.example.androidmovieminiproject.viewmodel.FavouriteViewModel;
import com.example.androidmovieminiproject.viewmodel.FilmDetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailMovieActivity extends BaseActivity {

    private FilmDetailViewModel viewModel;
    private FavouriteViewModel favouriteViewModel;
    private Favourite favourite;

    private ImageView poster;
    private TextView name;
    private TextView language;
    private TextView popularity;
    private TextView voteAverage;
    private TextView voteCount;
    private TextView overview;
    private ToggleButton favouriteToggle;
    private FloatingActionButton componentButtonBack;

    private LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initVariable();
        initButtonClick();
        setContentToView();
    }

    private void initVariable() {
        viewModel = new ViewModelProvider(this).get(FilmDetailViewModel.class);
        favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        poster = findViewById(R.id.movieDetailBackdrop);
        name = findViewById(R.id.movieDetailName);
        language = findViewById(R.id.movieDetailLanguage);
        popularity = findViewById(R.id.movieDetailPopularity);
        voteAverage = findViewById(R.id.movieDetailRating);
        voteCount = findViewById(R.id.movieDetailVoteCount);
        overview = findViewById(R.id.movieDetailDescription);
        favouriteToggle = findViewById(R.id.favouriteToggleButton);
        componentButtonBack = findViewById(R.id.componentButtonBack);
        loading = new LoadingDialog(DetailMovieActivity.this);

        loading.show();
    }

    private void setContentToView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(AppProperties.detailItemId, 0);
        String type = intent.getStringExtra(AppProperties.detailItemType);

        if (type.equalsIgnoreCase("tv")) {
            getTvDetail(id);
        } else if (type.equalsIgnoreCase("movie")) {
            getMovieDetail(id);
        }
    }

    private void getTvDetail(int id) {
        viewModel.findTvDetailById(id)
            .observe(this, tvDetail -> {
                if (tvDetail != null) {
                    favourite = setFavouriteFromTvDetail(tvDetail);
                    setTvDetail(tvDetail);
                }
            });
    }

    private void getMovieDetail(int id) {
        viewModel.findHomeDetailById(id)
                .observe(this, homeDetail -> {
                    if (homeDetail != null) {
                        favourite = setFavouriteFromHomeDetail(homeDetail);
                        setHomeDetail(homeDetail);
                    }
                });
    }

    private Favourite setFavouriteFromTvDetail(TvDetail tvDetail) {
        Favourite favourite = new Favourite();
        favourite.setItemId(tvDetail.getId());
        favourite.setBackdropUrl(tvDetail.getBackdropPath());
        favourite.setName(tvDetail.getName());
        favourite.setPosterUrl(tvDetail.getPosterPath());
        favourite.setVoteAverage(tvDetail.getVoteAverage());
        favourite.setVoteCount(tvDetail.getVoteCount());
        favourite.setType(AppProperties.tv);
        return favourite;
    }

    private Favourite setFavouriteFromHomeDetail(MovieDetail movieDetail) {
        Favourite favourite = new Favourite();
        favourite.setItemId(movieDetail.getId());
        favourite.setBackdropUrl(movieDetail.getBackdropPath());
        favourite.setName(movieDetail.getTitle());
        favourite.setPosterUrl(movieDetail.getPosterPath());
        favourite.setVoteAverage(movieDetail.getVoteAverage());
        favourite.setVoteCount(movieDetail.getVoteCount());
        favourite.setType(AppProperties.movie);
        return favourite;
    }

    private void setTvDetail(TvDetail tvDetail) {
        String posterUrl = ListAPI.URL_ORIGINAL_IMAGE.concat(tvDetail.getBackdropPath());
        Glide.with(this)
                .load(posterUrl)
                .centerCrop()
                .error(R.drawable.default_image)
                .placeholder(R.drawable.default_image)
                .fallback(R.drawable.default_image)
                .into(poster);

        name.setText(tvDetail.getName());
        language.setText(tvDetail.getLanguage());
        popularity.setText(tvDetail.getPopularity());
        voteAverage.setText(tvDetail.getVoteAverage());
        voteCount.setText(tvDetail.getVoteCount());
        overview.setText(tvDetail.getOverview());

        checkIsFavourite();

        loading.hide();
    }


    private void setHomeDetail(MovieDetail movieDetail) {
        String posterUrl = ListAPI.URL_ORIGINAL_IMAGE.concat(movieDetail.getBackdropPath());
        Glide.with(this)
                .load(posterUrl)
                .centerCrop()
                .error(R.drawable.default_image)
                .placeholder(R.drawable.default_image)
                .fallback(R.drawable.default_image)
                .into(poster);

        name.setText(movieDetail.getTitle());
        language.setText(movieDetail.getOriginalLanguage());
        popularity.setText(movieDetail.getPopularity());
        voteAverage.setText(movieDetail.getVoteAverage());
        voteCount.setText(movieDetail.getVoteCount());
        overview.setText(movieDetail.getOverview());

        checkIsFavourite();

        loading.hide();
    }

    private void checkIsFavourite() {
        favouriteViewModel.getAll()
            .observe(this, favourites -> {
                if (favourites != null & favourites.size() > 0) {
                    for (int i = 0; i < favourites.size(); i++) {
                        if (favourites.get(i).getItemId() == favourite.getItemId()) {
                            favouriteToggle.setChecked(true);
                            favourite.setItemId(favourites.get(i).getItemId());
                            break;
                        }
                    }
                }
            });
    }

    private void initButtonClick() {
        favouriteToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isChecked = favouriteToggle.isChecked();
                if (isChecked) {
                    favouriteToggle.setChecked(true);
                    favouriteViewModel.insert(favourite);
                } else {
                    favouriteToggle.setChecked(false);
                    favouriteViewModel.delete(favourite);
                }
            }
        });

        componentButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}