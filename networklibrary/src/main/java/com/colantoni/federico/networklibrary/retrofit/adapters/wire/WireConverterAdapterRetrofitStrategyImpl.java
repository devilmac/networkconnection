package com.colantoni.federico.networklibrary.retrofit.adapters.wire;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter;
import retrofit2.converter.wire.WireConverterFactory;


public class WireConverterAdapterRetrofitStrategyImpl implements ConverterAdapterRetrofitStrategy {

    @Override
    public Converter.Factory getConverterAdapterFactory(@Nullable final Object typeAdapter, final Object... typeAdapters) {

        return WireConverterFactory.create();
    }
}
