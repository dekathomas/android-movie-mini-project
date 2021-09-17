package com.example.androidmovieminiproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.activities.DetailMovieActivity;
import com.example.androidmovieminiproject.adapters.FavouriteAdapter;
import com.example.androidmovieminiproject.model.Favourite;
import com.example.androidmovieminiproject.utility.AppProperties;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;
import com.example.androidmovieminiproject.viewmodel.FavouriteViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends BaseFragment implements RecyclerViewClick {
    private RecyclerView recyclerView;
    private FavouriteViewModel favouriteViewModel;
    private FavouriteAdapter adapter;
    private LinearLayout emptyFavourite;
    private List<Favourite> favouriteList;
    private Button favouriteEmptyButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariable();
        initRecyclerView();
        setFavData();
    }

    private void initVariable() {
        emptyFavourite = getActivity().findViewById(R.id.emptyFavourite);
        favouriteEmptyButton = getActivity().findViewById(R.id.favouriteEmptyButton);
        favouriteViewModel = new ViewModelProvider(getActivity()).get(FavouriteViewModel.class);
        recyclerView = getActivity().findViewById(R.id.favouriteRecyclerView);

        initAnimationVariables(getActivity().findViewById(R.id.favouriteScrollView));
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setFavData() {
        favouriteViewModel.getAll().observe(getActivity(), favourites -> {
            if (favourites != null && favourites.size() > 0) {
                favouriteList = favourites;
                for (Favourite favourite : favourites) {
                    adapter = new FavouriteAdapter(favourites, this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            } else {
                emptyFavSection();
            }
            favouriteViewModel.getAll().removeObservers(getActivity());
            showScrollView();
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        getFavouriteById(position);
    }

    private void getFavouriteById(int position) {
        for (int i = 0; i < favouriteList.size(); i++) {
            if (position == i) {
                int itemId = favouriteList.get(i).getItemId();
                String type = favouriteList.get(i).getType();
                goToDetailPage(itemId, type);
                break;
            }
        }
    }

    private void goToDetailPage(int itemId, String type) {
        Intent intent = new Intent(getContext(), DetailMovieActivity.class);
        intent.putExtra(AppProperties.detailItemId, itemId);
        intent.putExtra(AppProperties.detailItemType, type);
        startActivity(intent);
    }

    private void emptyFavSection() {
        adapter = new FavouriteAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        emptyFavourite.setVisibility(View.VISIBLE);

        favouriteEmptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMoviePage();
            }
        });
    }

    private void gotoMoviePage() {
        BottomNavigationView bottomMenu = getActivity().findViewById(R.id.bottomMenu);
        bottomMenu.setSelectedItemId(R.id.firstMenuButton);
    }
}
