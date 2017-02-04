package com.colantoni.federico.networklibrary.retrofit.okhttp;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Class that provides the instance of {@link OkHttpClient}.
 */
public final class OkHttpModule {

    /**
     * Private constructor.
     */
    private OkHttpModule() {

    }

    /**
     * @return An instance of {@link OkHttpClient}.
     */
    public static OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        builder.addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }
}
