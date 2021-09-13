package com.example.androidmovieminiproject.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.adapters.Adaptery;
import com.example.androidmovieminiproject.adapters.MovieClickableCallback;
import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.api.TmdbApi;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.MovieModelClass;
import com.example.androidmovieminiproject.model.MovieOverview;
import com.example.androidmovieminiproject.repository.MovieDetailRepository;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {


    private MovieModelClass movieModelClass;

    private final MovieClickableCallback movieClickableCallback = new MovieClickableCallback() {
        @Override
        public void onClick(View view, MovieDetail movieDetail) {
            Gson gson = new Gson();
            String userString = gson.toJson(movieDetail);
            Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
        }
    };

    public Adaptery adaptery = new Adaptery(movieClickableCallback);

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
        movieModelClass.getRetrofitService()
                .getAPI()
                .getMovies(ListAPI.CATEGORY, ListAPI.APIKEY, ListAPI.LANGUAGE, ListAPI.PAGE)
                .enqueue(new Callback<List<MovieDetail>>() {
                    @Override
                    public void onResponse(Call<List<MovieDetail>> call, Response<List<MovieDetail>> response) {
                        adaptery.submitList(response.body());
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

        movieList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GetData getData = new GetData();
        getData.execute();
        return view;
    }

    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6";

    List<MovieOverview> movieList;
    RecyclerView recyclerView;



    public class GetData extends AsyncTask<String, String , String > {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i = 0; i< jsonArray.length() ; i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    MovieOverview model = new MovieOverview();
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImg(jsonObject1.getString("poster_path"));

                    movieList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            putDataIntoRecyclerView(movieList);

        }
    }


    public void putDataIntoRecyclerView(List<MovieOverview> movieList){
        Adaptery adaptery = new Adaptery(getActivity(), movieList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false));
        // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adaptery);
    }
}
*/