package com.example.androidmovieminiproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.androidmovieminiproject.api.RetrofitService;

public class MovieModelClass extends AndroidViewModel {
    private RetrofitService retrofitService;

    public MovieModelClass(@NonNull Application application) {
        super(application);
        retrofitService = new RetrofitService();
    }


    public RetrofitService getRetrofitService(){
        return  retrofitService;
    }

}




    /*
{

    String id;
    String name;
    String img;

    public MovieModelClass(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public MovieModelClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
*/