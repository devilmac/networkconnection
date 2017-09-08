package com.colantoni.federico.networklibrary.retrofit.adapters.protobuf


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import com.google.protobuf.ExtensionRegistryLite
import retrofit2.Converter
import retrofit2.converter.protobuf.ProtoConverterFactory


class ProtoBufConverterAdapterRetrofitStrategyImpl : ConverterAdapterRetrofitStrategy<ExtensionRegistryLite> {

    override fun getConverterAdapterFactory(vararg typeAdapter: ExtensionRegistryLite): Converter.Factory? {
        return if (typeAdapter.isNotEmpty()) {
            ProtoConverterFactory.createWithRegistry(typeAdapter[0])
        } else ProtoConverterFactory.create()
    }
}
