package com.colantoni.federico.networklibrary.builder

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class NetworkConnectionBuilder<T> {

    private lateinit var serviceClass: Class<T>

    private var okHttpClient = OkHttpClient()
    private var converterFactory: Array<out Converter.Factory> = arrayOf()
    private var useRxAdapter: Boolean? = false

    fun createSimpleWebservice(serviceUrl: String): T {
        val builder = Retrofit.Builder()
        builder.baseUrl(serviceUrl)

        val retrofit = builder.build()

        return retrofit.create(serviceClass)
    }

    fun getWebservice(serviceUrl: String): T {
        val builder = Retrofit.Builder()

        when (useRxAdapter) {
            true -> {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }
        }

        when (converterFactory.isEmpty()) {
            true -> {
                converterFactory.forEach {
                    builder.addConverterFactory(it)
                }
            }
        }

        builder.client(okHttpClient)
        builder.baseUrl(serviceUrl)
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }

    fun converterFactory(vararg converterFactory: Converter.Factory): NetworkConnectionBuilder<T> {
        this.converterFactory = converterFactory

        return this
    }

    fun usingRxAdapter(rxAdapter: Boolean): NetworkConnectionBuilder<T> {
        useRxAdapter = rxAdapter

        return this
    }

    fun okHttpClient(okHttpClient: OkHttpClient): NetworkConnectionBuilder<T> {
        this.okHttpClient = okHttpClient

        return this
    }
}