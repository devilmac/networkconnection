package com.colantoni.federico.networklibrary.retrofit.okhttp;


import android.content.Context;

import com.colantoni.federico.networklibrary.BuildConfig;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Class that provides the instance of {@link OkHttpClient}.
 */
public final class OkHttpModule {

    /**
     * The max size used to cache HTTP responses.
     */
    private static final double HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = Math.pow(2, 20);

    /**
     * Private constructor.
     */
    private OkHttpModule() {

    }

    /**
     * @param context The app/activity {@link Context} used.
     *
     * @return An instance of {@link OkHttpClient}.
     */
    public static OkHttpClient provideOkHttpClient(final Context context) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        File cacheBaseDir = context.getCacheDir();
        if (cacheBaseDir != null) {
            File cacheDir = new File(cacheBaseDir, "HttpResponseCache");
            builder.cache(new Cache(cacheDir, (long) HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);

        builder.addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }
}
