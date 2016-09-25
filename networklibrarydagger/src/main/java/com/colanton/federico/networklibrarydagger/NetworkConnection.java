package com.colanton.federico.networklibrarydagger;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkConnection {

    private final Retrofit.Builder retrofitBuilder;

    @Inject
    public NetworkConnection(final Retrofit.Builder builder) {

        this.retrofitBuilder = builder;
    }

    private void addTypeAdapterFactories(final Retrofit.Builder builder, final TypeAdapterFactory... typeAdapterFactories) {

        if (typeAdapterFactories.length > 0) {

            for (TypeAdapterFactory factory : typeAdapterFactories) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapterFactory(factory);

                GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());

                builder.addConverterFactory(gsonConverterFactory);
            }
        } else {

            builder.addConverterFactory(GsonConverterFactory.create());
        }
    }

    public <S> S initializeServiceInstance(final String baseUrl, final Class<S> serviceClass, final TypeAdapterFactory... typeAdapterFactories) {

        addTypeAdapterFactories(retrofitBuilder, typeAdapterFactories);

        retrofitBuilder.baseUrl(baseUrl);

        Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(serviceClass);
    }
}
