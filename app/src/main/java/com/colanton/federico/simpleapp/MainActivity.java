package com.colanton.federico.simpleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.colanton.federico.networklibrary.NetworkConnection;
import com.colanton.federico.simpleapp.service.MangaEdenService;
import com.colanton.federico.simpleapp.service.response.MangaEdenListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        super.onStart();

        NetworkConnection networkConnection = NetworkConnection.getInstance();
        networkConnection.createRetrofitInstance(this, "http://www.mangaeden.com/api/");
        MangaEdenService mangaEdenService = networkConnection.getRetrofitService(MangaEdenService.class);

        mangaEdenService.getAllMangaRx(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mangaEdenListResponse -> {

            Toast.makeText(MainActivity.this, "Total manga (Rx request): " + mangaEdenListResponse.getTotal(), Toast.LENGTH_LONG).show();
        }, Throwable::printStackTrace);

        mangaEdenService.getAllManga(0).enqueue(new Callback<MangaEdenListResponse>() {

            @Override
            public void onResponse(Call<MangaEdenListResponse> call, Response<MangaEdenListResponse> response) {

                Toast.makeText(MainActivity.this, "Total manga: " + response.body().getTotal(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MangaEdenListResponse> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}
