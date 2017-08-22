package com.colantoni.federico.networklibrary.retrofit.adapters;


import com.colantoni.federico.networklibrary.retrofit.adapters.custom.CustomConverterAdapterRetrofitStrategyImpl;
import com.colantoni.federico.networklibrary.retrofit.adapters.gson.GsonConverterAdapterRetrofitStrategyImpl;
import com.colantoni.federico.networklibrary.retrofit.adapters.jackson.JacksonConverterAdapterRetrotifImplStrategy;
import com.colantoni.federico.networklibrary.retrofit.adapters.moshi.MoshiConverterAdapterRetrofitStrategyImpl;
import com.colantoni.federico.networklibrary.retrofit.adapters.protobuf.ProtoBufConverterAdapterRetrofitStrategyImpl;
import com.colantoni.federico.networklibrary.retrofit.adapters.simplexml.SimpleXmlConverterAdapterRetrofitStrategyImpl;
import com.colantoni.federico.networklibrary.retrofit.adapters.simplexml.SimpleXmlNonStrictConverterAdapterRetrofitStrategyImpl;
import com.colantoni.federico.networklibrary.retrofit.adapters.wire.WireConverterAdapterRetrofitStrategyImpl;


public enum ConverterAdapterType {

    GSON(new GsonConverterAdapterRetrofitStrategyImpl()),

    MOSHI(new MoshiConverterAdapterRetrofitStrategyImpl()),

    WIRE(new WireConverterAdapterRetrofitStrategyImpl()),

    PROTOBUF(new ProtoBufConverterAdapterRetrofitStrategyImpl()),

    JACKSON(new JacksonConverterAdapterRetrotifImplStrategy()),

    SIMPLE_XML(new SimpleXmlConverterAdapterRetrofitStrategyImpl()),

    SIMPLE_XML_NON_STRICT(new SimpleXmlNonStrictConverterAdapterRetrofitStrategyImpl()),

    CUSTOM(new CustomConverterAdapterRetrofitStrategyImpl());

    private final ConverterAdapterRetrofitStrategy converterAdapterRetrofitStrategy;

    ConverterAdapterType(ConverterAdapterRetrofitStrategy converterAdapterRetrofitStrategy) {

        this.converterAdapterRetrofitStrategy = converterAdapterRetrofitStrategy;
    }

    public ConverterAdapterRetrofitStrategy getConverterAdapterRetrofitStrategy() {

        return converterAdapterRetrofitStrategy;
    }
}
