package com.example.androidmovieminiproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;


public class SearchFragment extends Fragment {
    public static  SearchFragment newInstance(){
        return new SearchFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup viewGroup,
                             @Nullable Bundle saveInstanceState){
        View root = inflater.inflate(R.layout.activity_search, viewGroup, false);
        RecyclerView recyclerView = root.findViewById(R.id.filmSearchRecylerView);

//        HomeAdapter homeAdapter = new HomeAdapter();
//        recyclerView.setAdapter(homeAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;

    }


}
