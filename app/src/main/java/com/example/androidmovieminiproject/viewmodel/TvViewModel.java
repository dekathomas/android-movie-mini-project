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
    private TvRepository tvRepository;
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
}
