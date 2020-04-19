package com.colantoni.federico.networklibrary.factories.converter

import retrofit2.Converter
import retrofit2.converter.wire.WireConverterFactory

object WireConverterAdapter : ConverterAdapter {
    override fun getConverterAdapter(): Converter.Factory = WireConverterFactory.create()
}
