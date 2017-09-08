package com.colantoni.federico.networklibrary.retrofit.adapters.custom


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import retrofit2.Converter.Factory


class CustomConverterAdapterRetrofitStrategyImpl : ConverterAdapterRetrofitStrategy<Factory> {
    override fun getConverterAdapterFactory(vararg typeAdapter: Factory): Factory? {
        return typeAdapter[0]
    }
}
