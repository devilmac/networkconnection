package com.colantoni.federico.networklibrary.retrofit.adapters;


import io.reactivex.annotations.Nullable;
import retrofit2.Converter.Factory;


public interface ConverterAdapterRetrofitStrategy<T> {

    Factory getConverterAdapterFactory(@Nullable final T typeAdapter, final T[] typeAdapters);
}
