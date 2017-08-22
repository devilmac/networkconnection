package com.colantoni.federico.networklibrary.retrofit.adapters.jackson;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter.Factory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class JacksonConverterAdapterRetrotifImplStrategy implements ConverterAdapterRetrofitStrategy<ObjectMapper> {

    @Override
    public Factory getConverterAdapterFactory(@Nullable final ObjectMapper objectMapper, final ObjectMapper... objectMappers) {

        if (objectMapper != null) {
            return JacksonConverterFactory.create(objectMapper);
        }

        if (objectMappers.length > 0) {
            return JacksonConverterFactory.create(objectMappers[0]);
        }

        return JacksonConverterFactory.create();
    }
}
