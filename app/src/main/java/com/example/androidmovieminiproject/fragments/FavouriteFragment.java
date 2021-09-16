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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.activities.DetailMovieActivity;
import com.example.androidmovieminiproject.adapters.FavouriteAdapter;
import com.example.androidmovieminiproject.model.Favourite;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.utility.RecyclerViewClick;
import com.example.androidmovieminiproject.viewmodel.FavouriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements RecyclerViewClick {
    private RecyclerView recyclerView;
    private FavouriteViewModel favouriteViewModel;
    private FavouriteAdapter adapter;
    private LoadingDialog loading;
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
        initViewModel();
        initRecyclerView();
        setFavData();
    }

    private void initVariable() {
        emptyFavourite = getActivity().findViewById(R.id.emptyFavourite);
        favouriteEmptyButton = getActivity().findViewById(R.id.favouriteEmptyButton);

        loading = new LoadingDialog(getActivity());
        loading.show();
    }

    private void initViewModel() {
        favouriteViewModel = new ViewModelProvider(getActivity()).get(FavouriteViewModel.class);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = getActivity().findViewById(R.id.favouriteRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setFavData() {
        favouriteViewModel.getAll()
            .observe(getActivity(), favourites -> {
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
            });
        loading.hide();
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
        intent.putExtra(String.valueOf(R.string.detail_item_id), itemId);
        intent.putExtra(String.valueOf(R.string.detail_item_type), type);
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
                getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new MovieFragment())
                .commitNow();
            }
        });
    }
}
