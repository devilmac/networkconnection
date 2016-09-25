package com.colanton.federico.simpleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.colanton.federico.networklibrary.NetworkConnection;
import com.colanton.federico.simpleapp.service.MangaEdenService;
import com.colanton.federico.simpleapp.service.response.immutables.GsonAdaptersImmutables;
import com.colanton.federico.simpleapp.service.response.immutables.MangaEdenListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://www.mangaeden.com/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        super.onStart();

        NetworkConnection networkConnection = NetworkConnection.getInstance();
        MangaEdenService mangaEdenServiceWithImmutables = networkConnection.initializeServiceInstance(this, BASE_URL, MangaEdenService.class, new GsonAdaptersImmutables());

        mangaEdenServiceWithImmutables.getAllMangaImmutablesRx(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mangaEdenListResponse -> {

            Toast.makeText(MainActivity.this, "Total manga (Rx request) - Immutables: " + mangaEdenListResponse.total(), Toast.LENGTH_LONG).show();
        }, Throwable::printStackTrace);

        mangaEdenServiceWithImmutables.getAllMangaImmutables(0).enqueue(new Callback<MangaEdenListResponse>() {

            @Override
            public void onResponse(Call<MangaEdenListResponse> call, Response<MangaEdenListResponse> response) {

                Toast.makeText(MainActivity.this, "Total manga - Immutables: " + response.body().total(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MangaEdenListResponse> call, Throwable t) {

                t.printStackTrace();
            }
        });

        MangaEdenService mangaEdenService = networkConnection.initializeServiceInstance(this, BASE_URL, MangaEdenService.class);

        mangaEdenService.getAllMangaRx(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mangaEdenListResponse -> {

            Toast.makeText(MainActivity.this, "Total manga (Rx request) - standard Gson: " + mangaEdenListResponse.getTotal(), Toast.LENGTH_LONG).show();
        }, Throwable::printStackTrace);

        mangaEdenService.getAllManga(0).enqueue(new Callback<com.colanton.federico.simpleapp.service.response.gson.MangaEdenListResponse>() {

            @Override
            public void onResponse(Call<com.colanton.federico.simpleapp.service.response.gson.MangaEdenListResponse> call, Response<com.colanton.federico.simpleapp.service.response.gson.MangaEdenListResponse> response) {

                Toast.makeText(MainActivity.this, "Total manga - standard Gson: " + response.body().getTotal(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<com.colanton.federico.simpleapp.service.response.gson.MangaEdenListResponse> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}
