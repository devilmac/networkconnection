package com.colantoni.federico.networklibrary.factories.converter

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

object JacksonConverterAdapter : ConverterAdapter, ConverterAdapter.WithParameter<ObjectMapper> {
    override fun getConverterAdapter(): Converter.Factory = JacksonConverterFactory.create()

    override fun getConverterAdapter(param: ObjectMapper): Converter.Factory =
            JacksonConverterFactory.create(param)
}
