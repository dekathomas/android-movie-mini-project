package com.example.androidmovieminiproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.Favourite;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private List<Favourite> favouriteList;
    private RecyclerViewClick listener;

    public FavouriteAdapter(List<Favourite> favouriteList, RecyclerViewClick listener) {
        this.favouriteList = favouriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_search_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favourite favourite = favouriteList.get(position);
        String posterUrl = favourite.getPosterUrl() != null ?
                ListAPI.URL_ORIGINAL_IMAGE.concat(favourite.getPosterUrl()) :
                ListAPI.URL_ORIGINAL_IMAGE.concat("/wwemzKWzjKYJFfCeiB57q3r4Bcm.png");

        Glide.with(holder.itemView)
                .load(posterUrl)
                .centerCrop()
                .error(R.drawable.default_image)
                .placeholder(R.drawable.default_image)
                .fallback(R.drawable.default_image)
                .into(holder.backdrop);

        holder.name.setText(favourite.getName());
        holder.voteAverage.setText(favourite.getVoteAverage());
        holder.voteCount.setText(favourite.getVoteCount());
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView backdrop;
        private TextView name;
        private TextView voteAverage;
        private TextView voteCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            backdrop = itemView.findViewById(R.id.searchMovieImage);
            name = itemView.findViewById(R.id.searchMovieName);
            voteAverage = itemView.findViewById(R.id.searchMovieVoteAverage);
            voteCount = itemView.findViewById(R.id.searchMovieCount);

            openDetailPage(itemView);
        }

        private void openDetailPage(View view) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAbsoluteAdapterPosition(), "");
                }
            });
        }
    }

}
