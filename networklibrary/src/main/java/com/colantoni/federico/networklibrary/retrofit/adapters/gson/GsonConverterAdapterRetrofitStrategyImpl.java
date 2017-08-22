package com.colantoni.federico.networklibrary.retrofit.adapters.gson;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter.Factory;
import retrofit2.converter.gson.GsonConverterFactory;


public class GsonConverterAdapterRetrofitStrategyImpl implements ConverterAdapterRetrofitStrategy<TypeAdapterFactory> {

    @Override
    public Factory getConverterAdapterFactory(@Nullable final TypeAdapterFactory typeAdapterFactory, final TypeAdapterFactory... typeAdapterFactories) {

        GsonBuilder gsonBuilder = new GsonBuilder();

        if (typeAdapterFactory != null) {
            gsonBuilder.registerTypeAdapterFactory(typeAdapterFactory);
        }

        if (typeAdapterFactories.length > 0) {
            for (TypeAdapterFactory factory : typeAdapterFactories) {
                gsonBuilder.registerTypeAdapterFactory(factory);
            }
        }

        return GsonConverterFactory.create(gsonBuilder.create());
    }
}
