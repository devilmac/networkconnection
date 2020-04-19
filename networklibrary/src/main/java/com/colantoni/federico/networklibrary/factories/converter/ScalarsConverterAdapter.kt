package com.colantoni.federico.networklibrary.factories.converter

import retrofit2.Converter
import retrofit2.converter.scalars.ScalarsConverterFactory

object ScalarsConverterAdapter : ConverterAdapter {
    override fun getConverterAdapter(): Converter.Factory = ScalarsConverterFactory.create()
}
