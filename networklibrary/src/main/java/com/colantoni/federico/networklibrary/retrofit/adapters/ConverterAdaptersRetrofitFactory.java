package com.colantoni.federico.networklibrary.retrofit.adapters;


import io.reactivex.annotations.Nullable;

import static retrofit2.Converter.Factory;


public class ConverterAdaptersRetrofitFactory<T> {

    private T typeAdapter;

    private T[] typeAdapters;


    @SafeVarargs
    public ConverterAdaptersRetrofitFactory(@Nullable final T typeAdapter, final T... typeAdapters) {

        this.typeAdapter = typeAdapter;
        this.typeAdapters = typeAdapters;
    }

    public Factory getTypeAdapterRetrofit(ConverterAdapterType converterAdapterType) {

        return converterAdapterType.getConverterAdapterRetrofitStrategy().getConverterAdapterFactory(typeAdapter, typeAdapters);
    }
}
