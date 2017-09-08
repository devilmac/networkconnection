package com.colantoni.federico.networklibrary.retrofit.adapters.moshi


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Converter.Factory
import retrofit2.converter.moshi.MoshiConverterFactory


class MoshiConverterAdapterRetrofitStrategyImpl : ConverterAdapterRetrofitStrategy<JsonAdapter<*>> {

    override fun getConverterAdapterFactory(vararg typeAdapter: JsonAdapter<*>): Factory? {
        val moshiBuilder = Moshi.Builder()

        typeAdapter.forEach { moshiBuilder.add(it) }

        return MoshiConverterFactory.create(moshiBuilder.build())
    }
}
