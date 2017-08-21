package com.colantoni.federico.networklibrary.retrofit.adapters.custom;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter.Factory;


public class CustomConverterAdapterRetrofitStrategyImpl implements ConverterAdapterRetrofitStrategy<Factory> {

    @Override
    public Factory getConverterAdapterFactory(@Nullable final Factory factory, final Factory... factories) {

        if (factory != null) {
            return factory;
        }

        if (factories.length > 0) {
            return factories[0];
        }

        return null;
    }
}
