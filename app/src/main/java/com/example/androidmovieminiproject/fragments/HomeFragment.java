package com.example.androidmovieminiproject.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.adapters.Adaptery;
import com.example.androidmovieminiproject.adapters.MovieClickableCallback;
import com.example.androidmovieminiproject.model.MovieDetail.MovieDetail;
import com.example.androidmovieminiproject.model.MovieModelClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class HomeFragment extends Fragment {
    private MovieModelClass movieModelClass;

    private RecyclerView recyclerView;
    private Adaptery adaptery;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private final MovieClickableCallback movieClickableCallback = new MovieClickableCallback() {
        @Override
        public void onClick(View view, MovieDetail movie) {
            Gson gson = new Gson();
            String userString = gson.toJson(movie);
            Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieModelClass = new ViewModelProvider(requireActivity()).get(MovieModelClass.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adaptery = new Adaptery(movieClickableCallback);
        recyclerView.setAdapter(adaptery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieModelClass.getRetrofitService();

    }

}



/*
{

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

    List<MovieModelClass> movieList;
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
                    MovieModelClass model = new MovieModelClass();
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


    public void putDataIntoRecyclerView(List<MovieModelClass> movieList){
        Adaptery adaptery = new Adaptery(getActivity(), movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adaptery);
    }
}
*/