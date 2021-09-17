package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.repository.TvRepository;
import com.example.androidmovieminiproject.utility.AlertDialog;

import java.util.List;

public class TvViewModel extends AndroidViewModel {
    private final TvRepository tvRepository;
    public MutableLiveData<List<TvDetail>> tvList = new MutableLiveData<>();;

    public TvViewModel(@NonNull Application application) {
        super(application);

        tvRepository = new TvRepository(application);
    }

    public void getAllTvPopular() {
        tvRepository.getAllPopularTv(new TvRepository.requestCallback() {
            @Override
            public void onSuccess(List<TvDetail> tvDetailList) {
                if (tvDetailList != null && tvDetailList.size() != 0) {
                    tvList.postValue(tvDetailList);
                }
                else {
                    tvList.postValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), String.valueOf(R.string.error_general));
                tvList.postValue(null);
            }
        });
    }

    public TvDetail getTv(int position) {
        if (tvList == null || tvList.getValue().size() == 0) {
            return null;
        }

        return tvList.getValue().get(position);
    }

    public void insertTvDetail(TvDetail tvDetail) {
        tvRepository.insertTvDetail(tvDetail);
    }
}
