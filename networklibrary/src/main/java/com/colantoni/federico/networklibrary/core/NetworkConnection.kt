package com.colantoni.federico.networklibrary.core

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton class to be used as HTTP request manager.
 */
class NetworkConnection constructor(urlString: String, private val okHttpClient: OkHttpClient = OkHttpClient(), private val converterFactory: Converter.Factory? = null, val useRxAdapter: Boolean? = false) {
    private val serviceBaseUrl: String = urlString

    /**
     * This method initializes an instance of the service you want to use.
     *
     * @param serviceClass The kind of service to be used.
     *
     * @return An initialized instance of the specified service class.
    </S> */
    fun <S> initServiceInstance(serviceClass: Class<S>): S {
        val builder = initRetrofitInstance()

        when (converterFactory != null) {
            true -> {
                builder.addConverterFactory(converterFactory)
            }
            else -> {
                builder.addConverterFactory(GsonConverterFactory.create())
            }
        }

        builder.client(okHttpClient)
        builder.baseUrl(serviceBaseUrl)
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }

    private fun initRetrofitInstance(): Retrofit.Builder {
        val builder = Retrofit.Builder()

        return when {
            this.useRxAdapter!! -> {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }
            else -> {
                builder
            }
        }
    }
}
