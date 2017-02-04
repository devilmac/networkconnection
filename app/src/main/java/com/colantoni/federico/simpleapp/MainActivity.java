package com.colantoni.federico.simpleapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.colantoni.federico.networklibrary.NetworkConnection;
import com.colantoni.federico.simpleapp.service.MangaEdenService;
import com.colantoni.federico.simpleapp.service.response.immutables.GsonAdaptersImmutables;
import com.colantoni.federico.simpleapp.service.response.immutables.MangaEdenListResponse;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://www.mangaeden.com/api/";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        super.onStart();

        NetworkConnection.setBaseUrl(BASE_URL);

        MangaEdenService mangaEdenServiceWithImmutables = NetworkConnection.initializeServiceInstance(MangaEdenService.class, getOkHttpClient(), new GsonAdaptersImmutables());

        mangaEdenServiceWithImmutables.getAllMangaImmutablesRx(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mangaEdenListResponse -> Toast.makeText
                (MainActivity.this, "Total manga (Rx request) - Immutables: " + mangaEdenListResponse.total(), Toast.LENGTH_LONG).show(), Throwable::printStackTrace);

        mangaEdenServiceWithImmutables.getAllMangaImmutables(0).enqueue(new Callback<MangaEdenListResponse>() {

            @Override
            public void onResponse(final Call<MangaEdenListResponse> call, final Response<MangaEdenListResponse> response) {

                Toast.makeText(MainActivity.this, "Total manga - Immutables: " + response.body().total(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(final Call<MangaEdenListResponse> call, final Throwable t) {

                Log.e("RETROFIT", t.getLocalizedMessage(), t);
            }
        });

        MangaEdenService mangaEdenService = NetworkConnection.initializeServiceInstance(MangaEdenService.class, null);

        mangaEdenService.getAllMangaRx(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mangaEdenListResponse -> Toast.makeText(MainActivity.this, "Total manga "
                + "(Rx request) - standard Gson: " + mangaEdenListResponse.getTotal(), Toast.LENGTH_LONG).show(), Throwable::printStackTrace);

        mangaEdenService.getAllManga(0).enqueue(new Callback<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse>() {

            @Override
            public void onResponse(final Call<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse> call, final Response<com.colantoni.federico.simpleapp.service.response
                    .gson.MangaEdenListResponse> response) {

                Toast.makeText(MainActivity.this, "Total manga - standard Gson: " + response.body().getTotal(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(final Call<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse> call, final Throwable t) {

                Log.e("RETROFIT", t.getLocalizedMessage(), t);
            }
        });
    }

    @NonNull
    private OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.cache(new Cache(getCacheDir(), 1024 * 1000));
        builder.addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }
}
