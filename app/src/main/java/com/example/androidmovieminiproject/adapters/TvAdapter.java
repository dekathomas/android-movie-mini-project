package com.example.androidmovieminiproject.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private List<TvDetail> tvList;
    private RecyclerViewClick listener;
    private ArrayList<TvDetail> tvListCopy;

    public TvAdapter(List<TvDetail> tvList, RecyclerViewClick listener) {
        this.tvList = tvList;
        this.tvListCopy = new ArrayList<>();
        tvListCopy.addAll(tvList);
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
        String posterUrl = ListAPI.URL_ORIGINAL_IMAGE.concat(tvDetail.getPosterPath());

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
                    listener.onItemClick(getAbsoluteAdapterPosition());
                }
            });
        }
    }

    public void filter(CharSequence charSequence){
        ArrayList<TvDetail> tempList= new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            for (TvDetail tvDetail : tvList){
                if(tvDetail.getName().toLowerCase().contains(charSequence)){
                    tempList.add(tvDetail);
                }
            }
        } else {
            tempList.addAll(tvListCopy);
        }
        tvList.clear();
        tvList.addAll(tempList);
        notifyDataSetChanged();
        tempList.clear();
    }

}
