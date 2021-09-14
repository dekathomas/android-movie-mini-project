package com.example.androidmovieminiproject.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.MovieModelClass;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeDetail> movieList;

    public HomeAdapter(List<HomeDetail> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDetail homeDetail = movieList.get(position);
        String posterUrl = ListAPI.URL_ORIGINAL_IMAGE.concat(homeDetail.getPosterPath());

        Glide.with(holder.itemView)
                .load(posterUrl)
                .centerCrop()
                .error(R.drawable.default_image)
                .placeholder(R.drawable.default_image)
                .fallback(R.drawable.default_image)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
