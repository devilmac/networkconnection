package com.colantoni.federico.networklibrary.retrofit.adapters.wire


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import retrofit2.Converter
import retrofit2.converter.wire.WireConverterFactory


class WireConverterAdapterRetrofitStrategyImpl : ConverterAdapterRetrofitStrategy<Any> {

    override fun getConverterAdapterFactory(vararg typeAdapter: Any): Converter.Factory? {
        return WireConverterFactory.create()
    }
}
