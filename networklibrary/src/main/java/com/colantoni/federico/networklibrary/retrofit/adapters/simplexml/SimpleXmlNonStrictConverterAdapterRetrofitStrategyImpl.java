package com.colantoni.federico.networklibrary.retrofit.adapters.simplexml;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;

import org.simpleframework.xml.Serializer;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class SimpleXmlNonStrictConverterAdapterRetrofitStrategyImpl implements ConverterAdapterRetrofitStrategy<Serializer> {

    @Override
    public Converter.Factory getConverterAdapterFactory(@Nullable final Serializer serializer, final Serializer... serializers) {

        if (serializer != null) {
            return SimpleXmlConverterFactory.createNonStrict(serializer);
        }

        if (serializers.length > 0) {
            return SimpleXmlConverterFactory.createNonStrict(serializers[0]);
        }

        return SimpleXmlConverterFactory.createNonStrict();
    }
}
