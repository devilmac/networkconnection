package com.colantoni.federico.simpleapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.colantoni.federico.networklibrary.NetworkConnection;
import com.colantoni.federico.simpleapp.service.MangaEdenService;
import com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponseManga;
import com.colantoni.federico.simpleapp.service.response.immutables.GsonAdaptersImmutables;
import com.colantoni.federico.simpleapp.service.response.immutables.MangaEdenListResponse;

import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterType.GSON;


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

        MangaEdenService mangaEdenServiceWithImmutables = NetworkConnection.initializeServiceInstance(MangaEdenService.class, GSON, new GsonAdaptersImmutables());

        int mangaLanguage = 1;

        mangaEdenServiceWithImmutables.getAllMangaImmutablesRx(mangaLanguage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MangaEdenListResponse>() {

            @Override
            public void accept(@io.reactivex.annotations.NonNull MangaEdenListResponse mangaEdenListResponse) throws Exception {

                Toast.makeText(MainActivity.this, "Total manga (Rx request) - Immutables: " + mangaEdenListResponse.total(), Toast.LENGTH_LONG).show();
            }
        }, new ThrowableConsumer());

        mangaEdenServiceWithImmutables.getAllMangaImmutables(0).enqueue(new Callback<MangaEdenListResponse>() {

            @Override
            public void onResponse(@NonNull final Call<MangaEdenListResponse> call, @NonNull final Response<MangaEdenListResponse> response) {

                Toast.makeText(MainActivity.this, "Total manga - Immutables: " + response.body().total(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull final Call<MangaEdenListResponse> call, @NonNull final Throwable t) {

                Log.e("RETROFIT", t.getLocalizedMessage(), t);
            }
        });

        // Using standard Gson ConverterFactory
        MangaEdenService mangaEdenService = NetworkConnection.initializeServiceInstance(MangaEdenService.class);

        //        mangaEdenService.getAllMangaRx(mangaLanguage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new MapResponseToMangaListFunction()).flatMap(new
        //        MapMangaListToMangaListItemFunction()).flatMap(new MapMangaItemToGgmMangaFunction(String.valueOf(mangaLanguage))).toList().subscribe(new MapGgmMangaListToConsumerFunction(), new
        //        ThrowableConsumer());
        //
        //        mangaEdenService.getAllManga(0).enqueue(new Callback<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse>() {
        //
        //            @Override
        //            public void onResponse(@NonNull final Call<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse> call, @NonNull final Response<com.colantoni.federico
        // .simpleapp
        //            .service.response.gson.MangaEdenListResponse> response) {
        //
        //                Toast.makeText(MainActivity.this, "Total manga - standard Gson: " + response.body().getTotal(), Toast.LENGTH_LONG).show();
        //            }
        //
        //            @Override
        //            public void onFailure(@NonNull final Call<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse> call, @NonNull final Throwable t) {
        //
        //                Log.e("RETROFIT", t.getLocalizedMessage(), t);
        //            }
        //        });
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

    private static class ThrowableConsumer implements Consumer<Throwable> {

        ThrowableConsumer() {

        }

        @Override
        public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            throwable.printStackTrace();
        }
    }


    private static class MapResponseToMangaListFunction implements Function<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse, List<MangaEdenListResponseManga>> {

        MapResponseToMangaListFunction() {

        }

        @Override
        public List<MangaEdenListResponseManga> apply(@io.reactivex.annotations.NonNull com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse mangaEdenListResponse) throws
        Exception {

            return mangaEdenListResponse.getManga();
        }
    }


    private static class MapMangaListToMangaListItemFunction implements Function<List<MangaEdenListResponseManga>, Publisher<MangaEdenListResponseManga>> {

        MapMangaListToMangaListItemFunction() {

        }

        @Override
        public Publisher<MangaEdenListResponseManga> apply(@io.reactivex.annotations.NonNull List<MangaEdenListResponseManga> mangaList) throws Exception {

            return Flowable.fromIterable(mangaList);
        }
    }


    private static class MapMangaItemToGgmMangaFunction implements Function<MangaEdenListResponseManga, Publisher<GGMManga>> {

        private String mangaLanguage;

        MapMangaItemToGgmMangaFunction(String mangaLanguage) {

            this.mangaLanguage = mangaLanguage;
        }

        @Override
        public Publisher<GGMManga> apply(@io.reactivex.annotations.NonNull MangaEdenListResponseManga manga) throws Exception {

            return Flowable.just(new GGMManga(manga.getT(), manga.getI(), Arrays.asList(manga.getC()), manga.getIm(), manga.getLd(), mangaLanguage));
        }
    }


    private static class GGMManga {

        private String title;

        private String mangaId;

        private List<String> category;

        private String imageUrl;

        private Double lastChapterDate;

        private String language;

        GGMManga(String title, String mangaId, List<String> category, String imageUrl, Double lastChapterDate, String language) {

            this.setTitle(title);
            this.setMangaId(mangaId);
            this.setCategory(category);
            this.setImageUrl(imageUrl);
            this.setLastChapterDate(lastChapterDate);
            this.setLanguage(language);
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {

            this.title = title;
        }

        public String getMangaId() {

            return mangaId;
        }

        public void setMangaId(String mangaId) {

            this.mangaId = mangaId;
        }

        public List<String> getCategory() {

            return category;
        }

        public void setCategory(List<String> category) {

            this.category = category;
        }

        public String getImageUrl() {

            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {

            this.imageUrl = imageUrl;
        }

        public Double getLastChapterDate() {

            return lastChapterDate;
        }

        public void setLastChapterDate(Double lastChapterDate) {

            this.lastChapterDate = lastChapterDate;
        }

        public String getLanguage() {

            return language;
        }

        public void setLanguage(String language) {

            this.language = language;
        }
    }


    private static class MapGgmMangaListToConsumerFunction implements Consumer<List<GGMManga>> {

        MapGgmMangaListToConsumerFunction() {

        }

        @Override
        public void accept(@io.reactivex.annotations.NonNull List<GGMManga> ggmMangas) throws Exception {

        }
    }
}
