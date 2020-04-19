package com.colantoni.federico.networklibrary.factories.converter

import retrofit2.Converter

interface ConverterAdapter {
    fun getConverterAdapter(): Converter.Factory

    interface WithParameter<T> {
        fun getConverterAdapter(param: T): Converter.Factory
    }
}
