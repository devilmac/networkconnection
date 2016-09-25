package com.colanton.federico.networklibrarydagger.module;

import android.content.Context;

import com.colanton.federico.networklibrarydagger.BuildConfig;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


@Module
public class OkHttpModule {

    private static final double HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = Math.pow(2, 20);

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final Context context, final HttpLoggingInterceptor httpLoggingInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        File cacheBaseDir = context.getCacheDir();
        if (cacheBaseDir != null) {
            File cacheDir = new File(cacheBaseDir, "HttpResponseCache");
            builder.cache(new Cache(cacheDir, (long) HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));
        }

        builder.addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }
}
