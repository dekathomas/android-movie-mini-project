package com.example.androidmovieminiproject.adapters;

import android.content.Context;
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
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.MovieModelClass;

import java.util.List;

public class Adaptery extends ListAdapter<MovieDetail, Adaptery.ViewHolder> {

    private final MovieClickableCallback movieClickableCallback;
    private Context imgContext;
    private List<MovieModelClass> mData;




    public Adaptery(@NonNull MovieClickableCallback movieClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<MovieDetail>() {
            @Override
            public boolean areItemsTheSame(@NonNull MovieDetail oldItem, @NonNull MovieDetail newItem) {
                return oldItem.getMovieId() == newItem.getMovieId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull MovieDetail oldItem, @NonNull MovieDetail newItem) {
                return oldItem.getMovieId() == newItem.getMovieId();
            }
        }).build());
//        this.mData = mData;
//        this.imgContext = imgContext;
        this.movieClickableCallback = movieClickableCallback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView movieTitle;
        TextView movieDesc;
        ImageView movieImage;


        @Override
        public void onClick(View view) {
            int position = getAbsoluteAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                MovieDetail movieDetail = getItem(position);
                movieClickableCallback.onClick(view, movieDetail);
            }

        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

    }

    @NonNull
    @Override
    public Adaptery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_detail, parent, false);
        Adaptery.ViewHolder viewHolder = new Adaptery.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull Adaptery.ViewHolder holder, int position) {
        Glide.with(imgContext)
                .load("https://image.tmdb.org/t/p/w780/"+mData.get(position).getRetrofitService())
                .into(holder.movieImage);
    }

}