package com.colantoni.federico.networklibrary.retrofit.adapters.gson


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import retrofit2.Converter.Factory
import retrofit2.converter.gson.GsonConverterFactory


class GsonConverterAdapterRetrofitStrategyImpl : ConverterAdapterRetrofitStrategy<TypeAdapterFactory> {

    override fun getConverterAdapterFactory(vararg typeAdapter: TypeAdapterFactory): Factory? {
        val gsonBuilder = GsonBuilder()

        typeAdapter.forEach { gsonBuilder.registerTypeAdapterFactory(it) }

        return GsonConverterFactory.create(gsonBuilder.create())
    }
}
