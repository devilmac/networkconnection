package com.colantoni.federico.networklibrary.retrofit.adapters.protobuf;


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy;
import com.google.protobuf.ExtensionRegistryLite;

import io.reactivex.annotations.Nullable;
import retrofit2.Converter;
import retrofit2.converter.protobuf.ProtoConverterFactory;


public class ProtoBufConverterAdapterRetrofitStrategyImpl implements ConverterAdapterRetrofitStrategy<ExtensionRegistryLite> {

    @Override
    public Converter.Factory getConverterAdapterFactory(@Nullable final ExtensionRegistryLite extensionRegistryLite, final ExtensionRegistryLite... extensionRegistryLites) {

        if (extensionRegistryLite != null) {
            return ProtoConverterFactory.createWithRegistry(extensionRegistryLite);
        }

        if (extensionRegistryLites.length > 0) {
            return ProtoConverterFactory.createWithRegistry(extensionRegistryLites[0]);
        }

        return ProtoConverterFactory.create();
    }
}
