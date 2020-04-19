package com.colantoni.federico.networklibrary.factories.converter

import com.squareup.moshi.Moshi
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

object MoshiConverterAdapter : ConverterAdapter, ConverterAdapter.WithParameter<Moshi> {
    override fun getConverterAdapter(): Converter.Factory = MoshiConverterFactory.create()

    override fun getConverterAdapter(param: Moshi): Converter.Factory =
            MoshiConverterFactory.create(param)
}
