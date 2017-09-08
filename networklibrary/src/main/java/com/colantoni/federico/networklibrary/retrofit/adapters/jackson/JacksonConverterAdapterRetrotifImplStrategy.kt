package com.colantoni.federico.networklibrary.retrofit.adapters.jackson


import com.colantoni.federico.networklibrary.retrofit.adapters.ConverterAdapterRetrofitStrategy
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Converter.Factory
import retrofit2.converter.jackson.JacksonConverterFactory


class JacksonConverterAdapterRetrotifImplStrategy : ConverterAdapterRetrofitStrategy<ObjectMapper> {

    override fun getConverterAdapterFactory(vararg typeAdapter: ObjectMapper): Factory? {
        return if (typeAdapter.isNotEmpty()) {
            JacksonConverterFactory.create(typeAdapter[0])
        } else JacksonConverterFactory.create()
    }
}
