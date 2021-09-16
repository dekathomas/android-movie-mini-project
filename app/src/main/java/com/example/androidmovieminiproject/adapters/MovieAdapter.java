package com.example.androidmovieminiproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final List<MovieDetail> movieList;
    private RecyclerViewClick listener;
    private String adapterType;

    public MovieAdapter(List<MovieDetail> movieList, RecyclerViewClick listener, String adapterType) {
        this.movieList = movieList;
        this.listener = listener;
        this.adapterType = adapterType;
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
        MovieDetail movieDetail = movieList.get(position);
        String posterUrl = movieDetail.getPosterPath() != null ?
                ListAPI.URL_ORIGINAL_IMAGE.concat(movieDetail.getPosterPath()) :
                ListAPI.URL_ORIGINAL_IMAGE.concat("/wwemzKWzjKYJFfCeiB57q3r4Bcm.png");

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAbsoluteAdapterPosition(), adapterType);
                }
            });
        }

    }

}
