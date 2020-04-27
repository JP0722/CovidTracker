package com.example.covidtracker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ImageView imageIcon;

    int position;



    private static final int MY_PERMISSION_REQUEST=1;
    Intent intents;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private long mLastClickTime = 0;
    ArrayList<String> arrayList;
    ArrayList<ExampleItem> exampleList=new ArrayList<>();
    ApiClass apiClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClass=retrofit.create(ApiClass.class);

        dostuff();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intents);
    }

    public void dostuff()
    {

        mRecyclerView = findViewById(R.id.rView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getMusic();



    }


    public void getMusic()
    {

        //exampleList.add(new ExampleItem(null,"Title","0", "0","0","0"));

        Toast.makeText(this, "GETTING DATA", Toast.LENGTH_SHORT).show();


        Call<CovidAll> call=apiClass.getCountriesdetails();
        call.enqueue(new Callback<CovidAll>() {
            @Override
            public void onResponse(Call<CovidAll> call, Response<CovidAll> response) {

                CovidAll covidAll=response.body();

                CovidGlobal covidGlobal=covidAll.getCovidGlobal();

                List<CovidCounties> covidCountiesList=covidAll.getCovidCountiesList();

                for(CovidCounties country:covidCountiesList)
                {

                    exampleList.add(new ExampleItem(null,
                            ""+country.getCountry(),
                            "Total Confirmed Cases:- "+country.getTotalConfirmed()+" (+"+country.getNewConfirmed()+")",
                            "TotalActive :- "+Integer.toString(Integer.parseInt(country.getTotalConfirmed())
                            -(Integer.parseInt(country.getTotalRecovered())+Integer.parseInt(country.getTotalDeaths()))),
                            "TotalRecovered :- "+country.getTotalRecovered(),
                            "Total Deaths :- "+country.getTotalDeaths()));


                }
               // mAdapter.filteredList(exampleList);
              //  mAdapter = new ExampleAdapter(exampleList);

                mAdapter = new ExampleAdapter(exampleList);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int getposition) {

                        Toast.makeText(MainActivity.this, Integer.toString(getposition), Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onFailure(Call<CovidAll> call, Throwable t) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.example_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              //  Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}


