package com.colanton.federico.networklibrarydagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


@Module
public class RetrofitBuilderModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(final OkHttpClient okHttpClient) {

        final Retrofit.Builder builder = new Retrofit.Builder();

        builder.client(okHttpClient);

        return builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }
}
