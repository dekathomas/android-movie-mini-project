package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.repository.TvRepository;
import com.example.androidmovieminiproject.utility.AlertDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;

import java.util.List;

public class TvViewModel extends AndroidViewModel {
    private final TvRepository tvRepository;
    public MutableLiveData<List<TvDetail>> tvList;

    public TvViewModel(@NonNull Application application) {
        super(application);
        tvRepository = new TvRepository(application);
        tvList = new MutableLiveData<>();
    }

    public void getAllTvPopular() {
        tvRepository.getAllPopularTv(new TvRepository.requestCallback() {
            @Override
            public void onSuccess(List<TvDetail> tvDetailList) {
                if (tvDetailList.size() != 0) {
                    tvList.setValue(tvDetailList);
                }
                else {
                    tvList.setValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), String.valueOf(R.string.error_general));
                tvList.setValue(null);
            }
        });
    }

    public TvDetail getTv(int position) {
        if (tvList.getValue().size() == 0) {
            return null;
        }

        return tvList.getValue().get(position);
    }

    public void insertTvDetail(TvDetail tvDetail) {
        tvRepository.insertTvDetail(tvDetail);
    }
}
