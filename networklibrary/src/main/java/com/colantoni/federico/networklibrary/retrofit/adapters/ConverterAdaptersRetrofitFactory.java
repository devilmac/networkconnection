package com.colantoni.federico.networklibrary.retrofit.adapters;


import io.reactivex.annotations.Nullable;

import static retrofit2.Converter.Factory;


public class ConverterAdaptersRetrofitFactory<T> {

    private T typeAdapter;


    public ConverterAdaptersRetrofitFactory(@Nullable final T typeAdapter) {

        this.typeAdapter = typeAdapter;
    }

    public Factory getTypeAdapterRetrofit(ConverterAdapterType converterAdapterType) {

        return converterAdapterType.getConverterAdapterRetrofitStrategy().getConverterAdapterFactory(typeAdapter);
    }
}
