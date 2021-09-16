package com.example.androidmovieminiproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.AppProperties;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private List<TvDetail> tvList;
    private RecyclerViewClick listener;

    public TvAdapter(List<TvDetail> tvList, RecyclerViewClick listener) {
        this.tvList = tvList;
        this.listener = listener;
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
        TvDetail tvDetail = tvList.get(position);
        String posterUrl = tvDetail.getPosterPath() != null ?
                ListAPI.URL_ORIGINAL_IMAGE.concat(tvDetail.getPosterPath()) :
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
        return tvList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAbsoluteAdapterPosition(), AppProperties.tv);
                }
            });
        }
    }
}
