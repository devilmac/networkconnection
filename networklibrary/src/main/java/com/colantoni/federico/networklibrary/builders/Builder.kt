package com.colantoni.federico.networklibrary.builders

import com.colantoni.federico.networklibrary.NetworkConnection
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter

interface Builder {
    fun reset()

    fun buildUrlConnectionString(connectionString: String):Builder

    fun buildOkHttpClient(client: OkHttpClient = OkHttpClient()):Builder

    fun buildCallAdapterFactory(adapter: CallAdapter.Factory?): Builder

    fun buildConverterAdapterFactory(converter: Converter.Factory?): Builder

    fun build(): NetworkConnection
}
