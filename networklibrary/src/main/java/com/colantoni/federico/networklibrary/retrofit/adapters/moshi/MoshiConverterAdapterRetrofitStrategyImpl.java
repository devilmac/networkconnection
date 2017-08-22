package com.colantoni.federico.networklibrary.retrofit.adapters.moshi;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter.Factory;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class MoshiConverterAdapterRetrofitStrategyImpl implements ConverterAdapterRetrofitStrategy<JsonAdapter> {

    @Override
    public Factory getConverterAdapterFactory(@Nullable final JsonAdapter jsonAdapter, final JsonAdapter... jsonAdapters) {

        Moshi.Builder moshiBuilder = new Moshi.Builder();

        if (jsonAdapter != null) {
            moshiBuilder.add(jsonAdapter);
        }

        if (jsonAdapters.length > 0) {
            for (JsonAdapter adapter : jsonAdapters) {
                moshiBuilder.add(adapter);
            }
        }

        return MoshiConverterFactory.create(moshiBuilder.build());
    }
}
