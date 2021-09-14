package com.example.androidmovieminiproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.adapters.HomeAdapter;
import com.example.androidmovieminiproject.viewmodel.HomeViewModel;
import com.example.androidmovieminiproject.utility.LoadingDialog;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private HomeViewModel homeViewModel;
    private LoadingDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = new LoadingDialog(getActivity());
        loading.show();

        initViewModel();
        initPopularRecylcerView();
    }

    private void initViewModel() {
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.getMovieList();
    }

    private void initPopularRecylcerView() {
        recyclerView = getActivity().findViewById(R.id.tvPopularRecyclerView);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 3);

        homeViewModel.movieList.observe(getActivity(), tvDetails -> {
            homeAdapter = new HomeAdapter(homeViewModel.movieList.getValue());
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);
            homeAdapter.notifyDataSetChanged();
            loading.hide();
        });
    }
}

/*

    private MovieModelClass movieModelClass;
    private RetrofitService retrofitService;

    private final MovieClickableCallback movieClickableCallback = new MovieClickableCallback() {
        @Override
        public void onClick(View view, MovieDetail movieDetail) {
            Gson gson = new Gson();
            String userString = gson.toJson(movieDetail);
            Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
        }
    };

    public HomeAdapter homeAdapter = new HomeAdapter(movieClickableCallback);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movieModelClass= new ViewModelProvider(requireActivity()).get(MovieModelClass.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrofitService
                .getAPI()
                .getMovies(ListAPI.CATEGORY, ListAPI.APIKEY, ListAPI.LANGUAGE, ListAPI.PAGE)
                .enqueue(new Callback<List<MovieDetail>>() {
                    @Override
                    public void onResponse(Call<List<MovieDetail>> call, Response<List<MovieDetail>> response) {
                        homeAdapter.submitList(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<MovieDetail>> call, Throwable t) {
                        Snackbar.make(view, "Error : "+ t.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                    }
                });
    }


}


/*{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ListAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        Call<MovieDetail> call = tmdbApi.getMovies(ListAPI.CATEGORY, ListAPI.APIKEY, ListAPI.LANGUAGE, ListAPI.PAGE);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {

            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

            }
        });
    }
}


/*extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
}
*/