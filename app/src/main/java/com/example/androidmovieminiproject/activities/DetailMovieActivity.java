package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.Favourite;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.AlertDialog;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.viewmodel.FavouriteViewModel;
import com.example.androidmovieminiproject.viewmodel.MovieDetailViewModel;

public class DetailMovieActivity extends BaseActivity {

    private MovieDetailViewModel viewModel;
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

    private LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        getSupportActionBar().hide();

        loading = new LoadingDialog(DetailMovieActivity.this);
        loading.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initVariable();
        initButtonClick();
        setContentToView();
    }

    private void initVariable() {
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        poster = findViewById(R.id.movieDetailBackdrop);
        name = findViewById(R.id.movieDetailName);
        language = findViewById(R.id.movieDetailLanguage);
        popularity = findViewById(R.id.movieDetailPopularity);
        voteAverage = findViewById(R.id.movieDetailRating);
        voteCount = findViewById(R.id.movieDetailVoteCount);
        overview = findViewById(R.id.movieDetailDescription);
        favouriteToggle = findViewById(R.id.favouriteToggleButton);
    }

    private void setContentToView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(String.valueOf(R.string.detail_item_id), 0);
        String type = intent.getStringExtra(String.valueOf(R.string.detail_item_type));

        if (type.equalsIgnoreCase("tv")) {
            getTvDetail(id);
        } else if (type.equalsIgnoreCase("home")) {
            getHomeDetail(id);
        }
    }

    private void getTvDetail(int id) {
        viewModel.findTvDetailById(id, "tv")
            .observe(this, tvDetail -> {
                if (tvDetail != null) {
                    favourite = setFavouriteFromTvDetail(tvDetail);
                    setTvDetail(tvDetail);
                }
            });
    }

    private void getHomeDetail(int id) {
        viewModel.findHomeDetailById(id, "home")
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
        return favourite;
    }

    private Favourite setFavouriteFromHomeDetail(HomeDetail homeDetail) {
        Favourite favourite = new Favourite();
        favourite.setItemId(homeDetail.getId());
        favourite.setBackdropUrl(homeDetail.getBackdropPath());
        favourite.setName(homeDetail.getName());
        favourite.setPosterUrl(homeDetail.getPosterPath());
        favourite.setVoteAverage(homeDetail.getVoteAverage());
        favourite.setVoteCount(homeDetail.getVoteCount());
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


    private void setHomeDetail(HomeDetail homeDetail) {
        String posterUrl = ListAPI.URL_ORIGINAL_IMAGE.concat(homeDetail.getBackdropPath());
        Glide.with(this)
                .load(posterUrl)
                .centerCrop()
                .error(R.drawable.default_image)
                .placeholder(R.drawable.default_image)
                .fallback(R.drawable.default_image)
                .into(poster);

        name.setText(homeDetail.getName());
        language.setText(homeDetail.getOriginalLanguage());
        popularity.setText(homeDetail.getPopularity());
        voteAverage.setText(homeDetail.getVoteAverage());
        voteCount.setText(homeDetail.getVoteCount());
        overview.setText(homeDetail.getOverview());

        checkIsFavourite();

        loading.hide();
    }



    private void checkIsFavourite() {
        favouriteViewModel.getAll()
            .observe(this, favourites -> {
                for (int i = 0; i < favourites.size(); i++) {
                    if (favourites.get(i).getItemId() == favourite.getItemId()) {
                        favouriteToggle.setChecked(true);
                        favourite.setId(favourites.get(i).getId());
                        break;
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
    }
}